package com.shop.vtluan.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import com.shop.vtluan.config.VNPayConfig;
import com.shop.vtluan.model.Cart;
import com.shop.vtluan.model.Cart_detail;
import com.shop.vtluan.model.Order_detail;
import com.shop.vtluan.model.Orders;
import com.shop.vtluan.model.User;
import com.shop.vtluan.model.DTO.ReceiverDto;
import com.shop.vtluan.repository.Order_detailRepository;
import com.shop.vtluan.repository.OrdersRepository;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class VNPayService {

    private final CartService cartService;
    private final UserService userService;
    private final Cart_detailService cart_detailService;
    private final OrdersRepository ordersRepository;
    private final Order_detailRepository order_detailRepository;

    public VNPayService(CartService cartService, UserService userService, Cart_detailService cart_detailService,
            OrdersRepository ordersRepository, Order_detailRepository order_detailRepository) {
        this.cartService = cartService;
        this.userService = userService;
        this.cart_detailService = cart_detailService;
        this.ordersRepository = ordersRepository;
        this.order_detailRepository = order_detailRepository;
    }

    public String createOrder(long total, ReceiverDto receiverDto, String urlReturn) {
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String vnp_TxnRef = VNPayConfig.getRandomNumber(8);
        String vnp_IpAddr = "127.0.0.1";
        String vnp_TmnCode = VNPayConfig.vnp_TmnCode;
        String orderType = "order-type";

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(total * 100));
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        // vnp_Params.put("vnp_NameInfo", receiverDto.getReceiverName());
        // vnp_Params.put("vnp_PhoneNumberInfo", receiverDto.getReceiverPhoneNumber());
        // vnp_Params.put("vnp_AddressInfo", receiverDto.getReceiverAddress());
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_OrderInfo",
                "TNN: " + receiverDto.getReceiverName() + "- SĐT: " + receiverDto.getReceiverPhoneNumber()
                        + "- ĐC: " + receiverDto.getReceiverAddress());

        String locate = "vn";
        vnp_Params.put("vnp_Locale", locate);

        urlReturn += VNPayConfig.vnp_Returnurl;
        vnp_Params.put("vnp_ReturnUrl", urlReturn);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                // Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                try {
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.UTF_8.toString()));
                    // Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.UTF_8.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.UTF_8.toString()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNPayConfig.vnp_PayUrl + "?" + queryUrl;
        return paymentUrl;
    }

    public int orderReturn(HttpServletRequest request) {
        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = null;
            String fieldValue = null;
            try {
                fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.UTF_8.toString());
                fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.UTF_8.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }
        // get infor receiverDto
        String infor = request.getParameter("vnp_OrderInfo");
        String total = request.getParameter("vnp_Amount");

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = VNPayConfig.hashAllFields(fields);
        if (signValue.equals(vnp_SecureHash)) {
            if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                HttpSession session = request.getSession();
                String email = (String) session.getAttribute("emailSession");
                User user = this.userService.getUserByEmail(email);
                Cart cart = this.cartService.getCartByUser(user);
                List<Cart_detail> cart_details = cart.getCart_details();

                Orders order = new Orders();
                order.setInfor(infor);
                order.setToltalPrice(Double.parseDouble(total) / 100);
                order.setUser(user);
                order.setStatus("PENDING");
                this.ordersRepository.save(order);

                for (Cart_detail cart_detail : cart_details) {
                    Order_detail order_detail = new Order_detail();
                    order_detail.setOrders(order);
                    order_detail.setProducts(cart_detail.getProducts());
                    order_detail.setQuantity(cart_detail.getQuantity());
                    this.order_detailRepository.save(order_detail);
                    this.cart_detailService.deleteItemCart(cart_detail);
                }
                this.cartService.removeCart(cart);

                session.setAttribute("total", 0);
                return 1;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }

}
