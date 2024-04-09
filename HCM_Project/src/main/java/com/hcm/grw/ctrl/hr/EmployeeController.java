package com.hcm.grw.ctrl.hr;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hcm.grw.comm.Function;
import com.hcm.grw.config.CreateNewAuthService;
import com.hcm.grw.ctrl.login.NaverOAuth;
import com.hcm.grw.dto.hr.AuthDto;
import com.hcm.grw.dto.hr.CommonCodeDto;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.dto.hr.SnsInfoDto;
import com.hcm.grw.model.service.hr.CommonCodeService;
import com.hcm.grw.model.service.hr.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/hr/employee/**")
public class EmployeeController {

	@Autowired
	private CommonCodeService codeService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private CreateNewAuthService authService;
	
	@Autowired
	private NaverOAuth naverOAuth;
	
	
	@GetMapping("registEmpAdmin.do")
	public String registEmployee(Model model) {
		log.info("{} 임직원 등록 화면", Function.getMethodName());
		
		Map<String, Object> mapDept = new HashMap<String, Object>();
		mapDept.put("role", "DT");
		
		Map<String, Object> mapRank = new HashMap<String, Object>();
		mapRank.put("role", "RK");
		
		Map<String, Object> mapPosit = new HashMap<String, Object>();
		mapPosit.put("role", "PN");
		
		List<CommonCodeDto> deptList = codeService.selectAllRole(mapDept);
		List<CommonCodeDto> rankList = codeService.selectAllRole(mapRank);
		List<CommonCodeDto> positionList = codeService.selectAllRole(mapPosit);

		model.addAttribute("deptList", deptList);
		model.addAttribute("rankList", rankList);
		model.addAttribute("positionList", positionList);
		
		return "hr/employee/registEmpAdmin";
	}

	@PostMapping("registEmpAdminOk.do")
	public @ResponseBody void registEmpAdminOk(@RequestParam("empl_picture") List<MultipartFile> file, 
												@RequestParam Map<String, String> map, 
												HttpServletResponse resp,
												Authentication authentication) throws IOException {
		log.info("{} 임직원 등록처리", Function.getMethodName());
		log.info("input map : {}", map);
		log.info("MultipartFile : {}", file);
		resp.setContentType("text/html;charset=utf-8;");

		String empl_id = "";
		if(authentication == null) {
			Function.alertHistoryBack("로그인 정보가 없습니다.("+Function.getMethodName()+")", "/login/login.do", "");
			return;
		}else {
			empl_id = authentication.getName();
		}
		
		int dupCnt = employeeService.duplicationEmpEmail(map.get("empl_email"));
		if(dupCnt > 0) {
			Function.alertHistoryBack("이미 등록된 이메일입니다.("+Function.getMethodName()+")", "/login/login.do", "");
			return;
		}
		
		
        // Random 객체 생성
        Random random = new Random();
        // 8자리 숫자 생성
        int randomNumber = random.nextInt(90000000) + 10000000;
		

        EmployeeDto emp = new EmployeeDto();

		emp.setEmpl_pwd(String.valueOf(randomNumber));
        emp.setEmpl_birth(map.get("empl_birth").replace("-",""));
        emp.setEmpl_email(map.get("empl_email"));
        emp.setEmpl_name(map.get("empl_name"));
        emp.setEmpl_gender(map.get("empl_gender"));
        emp.setEmpl_fax(map.get("empl_fax"));
        emp.setEmpl_phone(map.get("empl_phone"));
        emp.setEmpl_tel(map.get("empl_tel"));
        emp.setEmpl_dept_cd(map.get("empl_dept_cd"));
        emp.setEmpl_rank_cd(map.get("empl_rank_cd"));
        emp.setEmpl_position_cd(map.get("empl_position_cd"));
        emp.setEmpl_joindate(map.get("empl_joindate"));
        emp.setEmpl_auth("ROLE_USER");
        emp.setEmpl_create_id(empl_id);
		log.info("등록값1 : {}", emp);

		if(file != null) {
			for(MultipartFile f : file){
				emp.setEmpl_picture(f.getBytes());
			}
		}
        
		log.info("등록값2 : {}", emp);
		
		int n = employeeService.registEmployee(emp);
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		if(n < 1) {
			sb.append("alert('등록 시 오류가 발생하였습니다.'); history.back();");
		}else {
			sb.append("alert('정상적으로 등록 되었습니다.');");
			sb.append("location.href='/hr/employee/listAdmin.do';");
		}
		sb.append("</script>");
		
		resp.getWriter().print(sb);
	}

	@GetMapping("empModify.do")
	public String empModify(Model model, Authentication authentication, HttpServletResponse resp) {
		log.info("{} 임직원 수정페이지 진입", Function.getMethodName());
		resp.setContentType("text/html;charset=utf-8;");
		
		String empl_id = "";
		if(authentication == null) {
			Function.alertHistoryBack("로그인 정보가 없습니다.("+Function.getMethodName()+")", "/login/login.do", "");
			return null;
		}else {
			empl_id = authentication.getName();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empl_id", empl_id);

		EmployeeDto empInfo = employeeService.selectOneEmployee(map);
		byte[] empPic = empInfo.getEmpl_picture();
		empInfo.setEmpl_picture_str(Function.blobImageToString(empPic));
		
		model.addAttribute("empInfo", empInfo);
		
		return "hr/employee/empModify";
	}	
	
	
	@PostMapping("empModifyOk.do")
	public @ResponseBody void empModifyOk(@RequestParam("empl_picture") List<MultipartFile> file, 
												@RequestParam Map<String, String> map, 
												Authentication authentication,
												HttpServletRequest req) throws IOException {
		log.info("{} 임직원정보 수정처리", Function.getMethodName());
		
        String empl_modify_id = "";
		if(authentication == null) {
			Function.alertHistoryBack("로그인 정보가 없습니다.("+Function.getMethodName()+")", "/login/login.do", "");
			return;
		}else {
			empl_modify_id = authentication.getName();
		}

        EmployeeDto emp = new EmployeeDto();

        emp.setEmpl_phone(map.get("empl_phone"));
        emp.setEmpl_tel(map.get("empl_tel"));
        emp.setEmpl_fax(map.get("empl_fax"));
		emp.setEmpl_modify_id(empl_modify_id);
        emp.setEmpl_id(map.get("empl_id"));
        
        if(!file.stream().allMatch(MultipartFile::isEmpty)) {
			for(MultipartFile f : file){
		        log.info("f.isEmpty() : {}", f.isEmpty());
		        log.info("f.getSize() : {}", f.getSize());
		        log.info("f.getContentType() : {}", f.getContentType());
				emp.setEmpl_picture(f.getBytes());
			}
		}
        else {
			emp.setEmpl_picture(null);
		}

		log.info("수정값 : {}", emp);
		
		int n = employeeService.updateEmployee(emp);
		String msg;
		if(n < 1) {
			Function.alertHistoryBack("수정 시 오류가 발생하였습니다.", "", "");
			return;
			//sb.append("alert('수정 시 오류가 발생하였습니다.'); history.back();");
		}else {
			/* Session정보 Update */
			EmployeeDto employeeDto = employeeService.getUserInfo(map.get("empl_id"));
			HttpSession session = req.getSession();
			//이미지 스트링 정보로 처리
			employeeDto.setEmpl_picture_str(Function.blobImageToString(employeeDto.getEmpl_picture()));
			//2진정보 초기화
			employeeDto.setEmpl_picture(null);
			session.setAttribute("userInfoVo", employeeDto);

			
			Function.alertLocation("정상적으로 수정 되었습니다.", "/hr/employee/empModify.do", "","","");
			return;
			//sb.append("alert('정상적으로 수정 되었습니다.');");
			//sb.append("location.href='/hr/employee/list.do';");
		}
	}


	// 차후 rest로 변경, 사원 간략정보 확인용
	@GetMapping("selectEmployeeOne.do")
	public @ResponseBody EmployeeDto selectEmployeeOne(@RequestParam(required = true) String empl_id, HttpServletResponse resp) {
		log.info("{} 임직원정보 수정페이지 진입", Function.getMethodName());
		resp.setContentType("text/html; charset=utf-8");
		
		if(StringUtils.isEmpty(empl_id)) {
			Function.alertHistoryBack("요청 사번이 없습니다.", "", "");
			return null;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empl_id", empl_id);

		EmployeeDto empInfo = employeeService.selectOneEmployee(map);
		byte[] empPic = empInfo.getEmpl_picture();
		empInfo.setEmpl_picture_str(Function.blobImageToString(empPic));
		
		return empInfo;
	}

	@GetMapping("updatePwd.do")
	public String chgPwd(Authentication authentication, 
						 HttpServletResponse resp) {
		log.info("{} 비밀번호 변경 진입", Function.getMethodName());
		resp.setContentType("text/html; charset=utf-8");
		
		if(authentication == null) {
			Function.alertHistoryBack("로그인 정보가 없습니다.("+Function.getMethodName()+")", "/login/login.do", "");
			return null;
		}
		
		return "hr/employee/updatePwd";
	}

	
	@PostMapping("updatePwdOk.do")
	public @ResponseBody void updatePwdOk(@RequestParam Map<String, Object> chgPwdMap,
							  Authentication authentication,
							  HttpServletResponse resp) {
		log.info("{} 비밀번호 변경 처리", Function.getMethodName());
		resp.setContentType("text/html; charset=utf-8");

		if(authentication == null) {
			Function.alertHistoryBack("로그인 정보가 없습니다.("+Function.getMethodName()+")", "", "");
			return;
		}

		EmployeeDto empDto = employeeService.getLogin(authentication.getName());
		String originPwd = empDto.getEmpl_pwd();
		log.info("originPwd : {}", originPwd);
		
		chgPwdMap.put("empl_modify_id", authentication.getName());
		chgPwdMap.put("empl_id", authentication.getName());

		boolean isPwFlag = encoder.matches(chgPwdMap.get("empl_pwd").toString(), originPwd);
		log.info("isPwFlag : {}", isPwFlag);
		
		if(isPwFlag) {
			chgPwdMap.put("empl_pwd", originPwd);

			int cnt = employeeService.updatePwd(chgPwdMap);
	
			if(cnt == 0) {
				Function.alertHistoryBack("비밀번호 변경에 실패하였습니다.", "", "");
			}else {
				Function.alertLocation("비밀번호 변경이 완료 되었습니다.", "/hr/employee/updatePwd.do", "", "", "");
			}
		}else {
			Function.alertHistoryBack("현재 비밀번호와 일치하지 않습니다.", "", "");
		}
	}
	
	
	@GetMapping("authAdminList.do")
	public String authAdminList(Model model) {
		log.info("{} 권한리스트", Function.getMethodName());
	
		List<EmployeeDto> lists = employeeService.getAuthList();
		
		model.addAttribute("lists", lists);
		
		return "hr/employee/authAdminList";
	}

	@GetMapping("registAuthAdmin.do")
	public String registAuthAdmin(Model model) {
		log.info("{} 권한등록", Function.getMethodName());
	
		List<AuthDto> authLists = employeeService.selectAuthAllList();
		
		model.addAttribute("authLists", authLists);
		
		return "hr/employee/registAuthAdmin";
	}
	
	@PostMapping(value="getUserInfoSearch.do", produces = "application/json;")
	public @ResponseBody String getUserInfoSearch(@RequestParam HashMap<String, String> map, HttpServletResponse resp) {
		log.info("{}  사원정보 검색 : {}", Function.getMethodName(), map);
		resp.setContentType("text/html; charset=UTF-8;");
		
		List<EmployeeDto> empDto = employeeService.getUserInfoSearch(map);
	
		ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(empDto);
        } catch (JsonProcessingException e) {
            log.error("JSON 변환 중 오류 발생", e);
            return "Failed to convert object to JSON";
        }        
	}

	@PostMapping("updateAuthAdminOk.do")
	public @ResponseBody void updateAuthAdminOk(@RequestParam Map<String, Object> authMap,
			  					Authentication authentication,
			  					HttpServletResponse resp,
			  					HttpServletRequest req) {
		log.info("{} 권한등록/수정/삭제처리 : {}", Function.getMethodName(), authMap);
		resp.setContentType("text/html; charset=UTF-8;");

		String type = authMap.get("type").toString();
		String empl_modify_id = "";
		String empl_id = authMap.get("empl_id").toString();
		if(authentication == null) {
			Function.alertHistoryBack("로그인 정보가 없습니다.("+Function.getMethodName()+")", "/login/login.do", "");
			return;
		}else {
			empl_modify_id = authentication.getName();
		}
		
		if(type.equals("D")) {
			authMap.put("empl_auth", "ROLE_USER");
		}
		authMap.put("empl_modify_id", empl_modify_id); 
		
		int cnt = employeeService.updateAuthEmployee(authMap);
		if(cnt > 0 ) {
			if(type.equals("D")) {
				Function.alertLocation("권한삭제가 완료 되었습니다.", "/hr/employee/authAdminList.do", "", "", "");
			}else if(type.equals("I")) {
				Function.alertLocation("권한등록이 완료 되었습니다.", "/hr/employee/authAdminList.do", "", "", "");
			}else {
				Function.alertLocation("권한수정이 완료 되었습니다.", "/hr/employee/modifyAuthAdmin.do?empl_id="+empl_id, "btn-danger", "", "");
			}
			
			if(empl_id.equals(empl_modify_id)) {
			    //Role정보 Update
			    //Security Role정보 Update(Session등록 포함)
				boolean flag = authService.createNewAuthentication(authentication, authentication.getName(), req);
				if(!flag) {
					log.info("{} - 인증등록 오류발생.", Function.getMethodName());
					Function.alertLocation("인증 재등록에 실패하였습니다.<br>다시 로그인하여 주세요.", "/login/login.do", "btn-danger", "", "");
					return;
				}

			}
			
			return;
		}else {
			Function.alertHistoryBack("권한등록에 오류가 발생하였습니다.", "", "");
			return;
		}
	}

	@GetMapping("modifyAuthAdmin.do")
	public String authAdminDetail(@RequestParam(required = true) String empl_id,
								Authentication authentication,
								HttpServletResponse resp,
								Model model) {
		log.info("{} 권한수정페이지", Function.getMethodName());

		if(authentication == null) {
			Function.alertHistoryBack("로그인 정보가 없습니다.("+Function.getMethodName()+")", "/login/login.do", "");
			return null;
		}
		
		EmployeeDto employeeDto = employeeService.getAuthDetail(empl_id);
		List<AuthDto> authLists = employeeService.selectAuthAllList();
		
		model.addAttribute("authInfo", employeeDto);
		model.addAttribute("authLists", authLists);
		
		return "hr/employee/modifyAuthAdmin";
	}
	
	
// =========================== 구분선 =========================================	
	
	
	@GetMapping("listAdmin.do")
	public String employeeAllList(Model model) {
		log.info("{} 사원정보 리스트 진입", Function.getMethodName());
		
		List<EmployeeDto> lists = employeeService.selectAllEmployee();
		
		Map<String, Object> mapDept = new HashMap<String, Object>();
		mapDept.put("role", "DT");
		
		Map<String, Object> mapRank = new HashMap<String, Object>();
		mapRank.put("role", "RK");
		
		Map<String, Object> mapPosit = new HashMap<String, Object>();
		mapPosit.put("role", "PN");
		
		List<CommonCodeDto> deptList = codeService.selectAllRole(mapDept);
		List<CommonCodeDto> rankList = codeService.selectAllRole(mapRank);
		List<CommonCodeDto> positionList = codeService.selectAllRole(mapPosit);

		model.addAttribute("deptList", deptList);
		model.addAttribute("rankList", rankList);
		model.addAttribute("positionList", positionList);
		model.addAttribute("lists", lists);
		
		return "hr/employee/listAdmin";
	}	

	@GetMapping("modifyAdmin.do")
	public void modifyAdmin(Model model ,String empl_id) {
		log.info("{} 임직원정보 상세(어드민)화면", Function.getMethodName());
		System.out.println(empl_id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empl_id", empl_id);
		EmployeeDto empInfo = employeeService.selectOneEmployee(map);
		byte[] empPic = empInfo.getEmpl_picture();
		empInfo.setEmpl_picture_str(Function.blobImageToString(empPic));
		model.addAttribute("empInfo", empInfo);
//		return "hr/employee/modifyAdmin";
		
	}	
	
	
	@PostMapping("modifyAdminOk.do")
	public @ResponseBody void modifyAdminOk(@RequestParam("empl_picture") List<MultipartFile> file, 
												@RequestParam Map<String, String> map, 
												Authentication authentication,
												HttpServletRequest req,
												String empl_id) throws IOException {
		log.info("{} 수정처리", Function.getMethodName());
		
		EmployeeDto emp = new EmployeeDto();

        emp.setEmpl_phone(map.get("empl_phone"));
        emp.setEmpl_tel(map.get("empl_tel"));
        emp.setEmpl_fax(map.get("empl_fax"));
		emp.setEmpl_modify_id(empl_id);
        emp.setEmpl_id(map.get("empl_id"));
        
        if(!file.stream().allMatch(MultipartFile::isEmpty)) {
			for(MultipartFile f : file){
		        log.info("f.isEmpty() : {}", f.isEmpty());
		        log.info("f.getSize() : {}", f.getSize());
		        log.info("f.getContentType() : {}", f.getContentType());
				emp.setEmpl_picture(f.getBytes());
			}
		}
        else {
			emp.setEmpl_picture(null);
		}

		log.info("수정값 : {}", emp);
		
		int n = employeeService.updateEmployee(emp);
		String msg;
		if(n < 1) {
			Function.alertHistoryBack("수정 시 오류가 발생하였습니다.", "", "");
			return;
			//sb.append("alert('수정 시 오류가 발생하였습니다.'); history.back();");
		}else {
			/* Session정보 Update */
			EmployeeDto employeeDto = employeeService.getUserInfo(map.get("empl_id"));
			HttpSession session = req.getSession();
			//이미지 스트링 정보로 처리
			employeeDto.setEmpl_picture_str(Function.blobImageToString(employeeDto.getEmpl_picture()));
			//2진정보 초기화
			employeeDto.setEmpl_picture(null);
			session.setAttribute("userInfoVo", employeeDto);

			
			Function.alertLocation("정상적으로 수정 되었습니다.", "/hr/employee/listAdmin.do", "","","");
			return;
			//sb.append("alert('정상적으로 수정 되었습니다.');");
			//sb.append("location.href='/hr/employee/list.do';");
		}
        
	}
	
	
	
	@PostMapping(value = "empSearching.do")
	@ResponseBody
	public List<EmployeeDto> empSearching(@RequestParam("empShCtgVal") String empShCtgVal,
								@RequestParam("empStaCtg") String empStaCtg,
								@RequestParam("startDate") String startDate,
								@RequestParam("endDate") String endDate,
								@RequestParam("empSearchValue") String empSearchValue,
								@RequestParam("dtArr") String[] dtArr,
								@RequestParam("rkArr") String[] rkArr,
								@RequestParam("pnArr") String[] pnArr
							) {
		log.info("{} 임직원정보(어드민) 검색", Function.getMethodName());
		System.out.println(empShCtgVal);
		System.out.println(empStaCtg);
		System.out.println(startDate);
		System.out.println(endDate);
		System.out.println(empSearchValue);
		System.out.println(Arrays.toString(dtArr));
		System.out.println(Arrays.toString(rkArr));
		System.out.println(Arrays.toString(pnArr));
		
		
		
		
		String searchCtg = StringUtils.defaultIfEmpty(empShCtgVal, null);
		String delCtg = StringUtils.defaultIfEmpty(empStaCtg, null);
		String start = StringUtils.defaultIfEmpty(startDate, null);
		String end = StringUtils.defaultIfEmpty(endDate, null);
		String searchVal = StringUtils.defaultIfEmpty(empSearchValue, null);
		
//		System.err.println(searchCtg);
//		System.err.println(delCtg);
//		System.err.println(start);
//		System.err.println(end);
//		System.err.println(searchVal);
//		System.err.println(Arrays.toString(dtArr));
//		System.err.println(Arrays.toString(rkArr));
//		System.err.println(Arrays.toString(pnArr));
		
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchCtg", searchCtg);
		searchMap.put("empl_name", searchVal);
		searchMap.put("empl_id", searchVal);
		searchMap.put("empl_phone", searchVal);
		searchMap.put("empl_delflag", delCtg);
		searchMap.put("startdate", start);
		searchMap.put("enddate", end);
		
		if(dtArr.length == 0) {
			dtArr = null;
		}else {
			searchMap.put("empl_dept_cd", "work");
		}
		
		if(rkArr.length == 0) {
			rkArr = null;
		}else{
			searchMap.put("empl_rank_cd", "work");
		}
		
		if(pnArr.length == 0) {
			pnArr = null;
		}else {
			searchMap.put("empl_position_cd", "work");
		}
		
		searchMap.put("dtArr", dtArr);
		searchMap.put("rkArr", rkArr);
		searchMap.put("pnArr", pnArr);
		
		
		List<EmployeeDto> searchlists = employeeService.searchAllEmployee(searchMap);
		System.out.println(searchlists);
		Gson returnList = new GsonBuilder().create();
		return searchlists;
	}
	
	
	
	@GetMapping(value = "empAdminValueChk.do")
	@ResponseBody
	public boolean empAdminValueChk(String empl_phone, String empl_tel, String empl_fax) {
		log.info("{} 임직원정보(어드민) 중복확인", Function.getMethodName());
		boolean returnBool = true;
		if(empl_phone != null) {
			System.out.println(empl_phone);
			returnBool = employeeService.chkEmpPhoneNum(empl_phone);
		}
		
		if(empl_tel != null) {
			System.out.println(empl_tel);
			returnBool = employeeService.chkEmpTelNum(empl_tel);
		}
		
		if(empl_fax != null) {
			System.out.println(empl_fax); 
			returnBool = employeeService.chkEmpFaxNum(empl_fax);
		}
		
		return returnBool;
	}
	
	
	@GetMapping("naverSns.do")
	public String naverSns(Model model,
						 Authentication authentication,
						 HttpServletRequest req) {
		log.info("{} 간편로그인 진입", Function.getMethodName());

		String empl_id = "";
		if(authentication == null) {
			Function.alertLocation("로그인 정보가 없습니다.("+Function.getMethodName()+")", "/login/login.do", "", "", "");
			return null;
		}else {
			empl_id = authentication.getName();
		}
		
		SnsInfoDto snsDto = employeeService.getSnsInfo(empl_id);
		if(snsDto != null) {
			model.addAttribute("snsInfo", snsDto);
		}else {
			String naverUrl = "";
			naverUrl += naverOAuth.getAuthUrl();
			naverUrl += "&client_id=".concat(naverOAuth.getClientId());
			try {
				naverUrl += "&redirect_uri=".concat(URLEncoder.encode(naverOAuth.getRedirectUrl(),"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				naverUrl += "&redirect_uri=";
			}
			String rndStr = naverOAuth.generateState();
			naverUrl += "&state=".concat(rndStr);
			
			req.getSession().setAttribute("state", rndStr);
			
			model.addAttribute("naverSnsUrl", naverUrl);
			model.addAttribute("snsInfo", "");
		}
		
		return "hr/employee/naverSns";
	}


	@GetMapping("delNaverSns.do")
	public @ResponseBody void delNaverSns(Model model,
										 Authentication authentication) {
		log.info("{} 간편로그인 삭제", Function.getMethodName());

		
		String empl_id = "";
		String emsn_id = "";
		if(authentication == null) {
			Function.alertHistoryBack("로그인 정보가 없습니다.("+Function.getMethodName()+")", "", "");
			return;
		}else {
			empl_id = authentication.getName();
		}

		SnsInfoDto snsDto = employeeService.getSnsInfo(empl_id);
		if(snsDto == null) {
			Function.alertLocation("네이버 간편로그인 조회 정보가 없습니다.", "/hr/employee/naverSns.do", "", "", "");
			return;
		}else {
			emsn_id = snsDto.getEmsn_id();
		}
		
		int cnt = employeeService.delSnsInfo(emsn_id);
		if(cnt>0) {
			Function.alertLocation("네이버 간편로그인 연결이 해제 되었습니다.", "/hr/employee/naverSns.do", "", "", "");
			return;
		}else {
			Function.alertLocation("네이버 간편로그인 연결 해제 오류가 발생하였습니다.", "/hr/employee/naverSns.do", "", "", "");
			return;
		}
		
		
	}
	
	
}
