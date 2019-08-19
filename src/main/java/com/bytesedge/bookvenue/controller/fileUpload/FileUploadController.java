package com.bytesedge.bookvenue.controller.fileUpload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bytesedge.bookvenue.common.DbService;
import com.bytesedge.bookvenue.controller.ControllerUtil;
import com.bytesedge.bookvenue.model.Docs;

@Controller
public class FileUploadController {
	//private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	private static String UPLOAD_LOCATION="/tmp/";
	
	@Autowired
	FileValidator fileValidator;

	@Autowired
	MultiFileValidator multiFileValidator;

	@InitBinder("fileBucket")
	protected void initBinderFileBucket(WebDataBinder binder) {
		binder.setValidator(fileValidator);
	}

	@InitBinder("multiFileBucket")
	protected void initBinderMultiFileBucket(WebDataBinder binder) {
		binder.setValidator(multiFileValidator);
	}

	@RequestMapping(value = "/app/singleUploadForm", method = RequestMethod.GET)
	public @ResponseBody ModelAndView getSingleUploadPage(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) {
		FileBucket fileModel = new FileBucket();
		model.addAttribute("fileBucket", fileModel);
		return new ModelAndView("fileUpload/singleFileUpload");
	}

	@RequestMapping(value = "/app/singleUpload", method = RequestMethod.POST)
	public @ResponseBody ModelAndView singleFileUpload(HttpServletRequest request, HttpServletResponse response,
			@Valid FileBucket fileBucket, BindingResult result, ModelMap model)
			throws IOException {
		MultipartFile multipartFile = fileBucket.getFile();
		String fileName = multipartFile.getOriginalFilename();
		if (result.hasErrors()) {
			return new ModelAndView("fileUpload/singleFileUpload");
		} else {
			FileCopyUtils.copy(fileBucket.getFile().getBytes(),
					new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
			Docs dbObj = new Docs();
			dbObj.setName(fileName);
			String fileSize = String.valueOf(fileBucket.getFile().getSize()) ;
			dbObj.setCreatedTime(new Date(System.currentTimeMillis()));
			dbObj.setCreatedUserId(ControllerUtil.getUserId(request));
			
			 try {
				DbService.getInstance().getSetupService().saveOrUpdate(dbObj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("fileName", fileName);
			model.addAttribute("status", "Success");
			return new ModelAndView("fileUpload/fileUploadResult");
		}
	}

	@RequestMapping(value = "/app/multiUploadForm", method = RequestMethod.GET)
	public @ResponseBody ModelAndView getMultiUploadPage(HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		MultiFileBucket filesModel = new MultiFileBucket();
		model.addAttribute("multiFileBucket", filesModel);
		return new ModelAndView( "fileUpload/multiFileUploader");
	}

	@RequestMapping(value = "/app/multiUpload", method = RequestMethod.POST, consumes = "multipart/form-data")
	public @ResponseBody ModelAndView multiFileUpload(HttpServletRequest request, HttpServletResponse response,
			@Valid MultiFileBucket multiFileBucket, BindingResult result, ModelMap model)
			throws IOException {

		if (result.hasErrors()) {
			System.out.println("validation errors in multi upload");
			return new ModelAndView("fileUpload/multiFileUploader");
		} else {
			System.out.println("Fetching files");
			List<String> fileNames = new ArrayList<String>();

			// Now do something with file...
			for (FileBucket bucket : multiFileBucket.getFiles()) {
				FileCopyUtils.copy(bucket.getFile().getBytes(),
						new File(UPLOAD_LOCATION + bucket.getFile().getOriginalFilename()));
				fileNames.add(bucket.getFile().getOriginalFilename());
			}

			model.addAttribute("fileNames", fileNames);
			return new ModelAndView("fileUpload/fileUploadMultiSuccess");
		}
	}
}
