package com.hange.booking.auth.config;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

	private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Lấy địa chỉ IP của client gửi request
		String clientIp = request.getRemoteAddr();

		// Lấy bucket tương ứng với IP, nếu chưa có thì tạo mới
		Bucket bucket = buckets.computeIfAbsent(clientIp, k -> createNewBucket());

		// Kiểm tra xem bucket có còn token để xử lý request không
		if (bucket.tryConsume(1)) {
			// ✅ Còn token: cho phép request tiếp tục đi qua các filter khác
			filterChain.doFilter(request, response);
		} else {
			// ❌ Hết token: từ chối xử lý request (HTTP 429 - Too Many Requests)
			response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
			response.setContentType("application/json; charset=UTF-8");

			// Gửi thông báo lỗi dạng JSON cho client
			response.getWriter().write("""
					    {
					      "status": 429,
					      "message": "Quá nhiều yêu cầu. Vui lòng thử lại sau!",
					      "data":"null",
					      "error":"Too many requests"
					    }
					""");
		}
	}

	/**
	 * Tạo một Bucket mới (thùng token) với giới hạn cố định. Mỗi IP có bucket
	 * riêng.
	 */
	private Bucket createNewBucket() {
		// Cấu hình: 10 yêu cầu (token) mỗi phút
		// Mỗi phút sẽ refill lại 10 token mới
		@SuppressWarnings("deprecation")
		Bandwidth limit = Bandwidth.classic(1000, // Số token tối đa trong 1 phút
				Refill.intervally(10, Duration.ofMinutes(1)) // Cách refill token
		);

		// Tạo bucket mới với cấu hình trên
		return Bucket.builder().addLimit(limit).build();
	}
}
