package com.example.coza_store.filter;

import com.example.coza_store.utils.JwtHelper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

//  Tất cả request đều phải chạy vào filter này
@Component
public class JwtFilter extends OncePerRequestFilter {
    /**
     *  Nhận được token truyền trên header
     *  Giải mã token
     *  Nếu giải mã thành công thì hợp lệ
     *  Tạo chứng thực và cho đi vào link người gọi
     */

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            //  Lấy giá trị của header có key là Authorization
            String header = request.getHeader("Authorization");
            if(header != null && header.startsWith("Bearer ")) {
                //  Cắt bỏ chữ Bearer và lấy ra token
                String token = header.substring(7);
                //  Giải mã token
                Claims claims = jwtHelper.decodeToken(token);
                if(claims != null) {
                    //  Tạo chứng thực cho Security
                    SecurityContext context = SecurityContextHolder.getContext();
                    UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken("", "", new ArrayList<>());
                    context.setAuthentication(user);
                }
                System.out.println(token);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        filterChain.doFilter(request,response);

    }
}
