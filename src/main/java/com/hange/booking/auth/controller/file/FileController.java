package com.hange.booking.auth.controller.file;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hange.booking.auth.service.file.FileService;
import com.hange.booking.auth.utils.FormatResponse.ApiResponseFormat;
import com.hange.booking.auth.utils.FormatResponse.ApiResponseUtil;

@RestController
@RequestMapping("/api/v1/files")
public class FileController {
	@Autowired
	private FileService fileService;

	@PostMapping
	public ResponseEntity<ApiResponseFormat> uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam("folder") String folder) {
		fileService.validateFile(file);
		Map<String, String> response = new HashMap<>();
		try {
			fileService.createUploadFolder(folder);
			String finalName = fileService.store(file, folder);
			response.put("filename", finalName);
			response.put("Upload at", Instant.now().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(ApiResponseUtil.success(response, HttpStatus.OK.value()));
	}

	@GetMapping("")
	public ResponseEntity<ApiResponseFormat> download(
			@RequestParam(name = "filename", required = false) String fileName,
			@RequestParam(name = "folder", required = false) String folder) {
		Resource resource = fileService.getResource(fileName, folder);

		return ResponseEntity.ok().body(ApiResponseUtil.success(resource, HttpStatus.OK.value()));
	}

}
