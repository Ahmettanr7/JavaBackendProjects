package AhmetTanrikulu.HRMSBackend.api.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import AhmetTanrikulu.HRMSBackend.business.abstracts.AbilityService;
import AhmetTanrikulu.HRMSBackend.business.abstracts.EducationService;
import AhmetTanrikulu.HRMSBackend.business.abstracts.ExperienceService;
import AhmetTanrikulu.HRMSBackend.business.abstracts.ImageService;
import AhmetTanrikulu.HRMSBackend.business.abstracts.LanguageService;
import AhmetTanrikulu.HRMSBackend.business.abstracts.SingleInformationService;
import AhmetTanrikulu.HRMSBackend.core.Service.CloudinaryService;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.ErrorDataResult;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Ability;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Education;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Experience;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Image;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Language;
import AhmetTanrikulu.HRMSBackend.entities.concretes.SingleInformation;

@RestController
@RequestMapping("")
@CrossOrigin
public class CurriculumVitaesController {
	private AbilityService abilityService;
	private EducationService educationService;
	private ExperienceService experienceService;
	private ImageService imageService;
	private LanguageService languageService;
	private SingleInformationService singleInformationService;
	private CloudinaryService cloudinaryService;

	@Autowired
	public CurriculumVitaesController(
			 AbilityService abilityService,
			 EducationService educationService,
			 ExperienceService experienceService,
			 ImageService imageService,
			 LanguageService languageService,
			 SingleInformationService singleInformationService,
			 CloudinaryService cloudinaryService
			) {
		super();
		this.abilityService = abilityService;
		this.educationService = educationService;
		this.experienceService = experienceService;
		this.imageService = imageService;
		this.languageService = languageService;
		this.singleInformationService = singleInformationService;
		this.cloudinaryService = cloudinaryService;
	}
	
											//ABILITIES CONTROLLER
	@GetMapping("/api/cvs/abilities/getall")
	public DataResult<List<Ability>> getAllAbilities(){
		return this.abilityService.getAll();
	}
	
	@GetMapping("/api/cvs/abilities/getbyuserid")
	public DataResult<List<Ability>> getAbilitiesByUserId(int userId){
		return this.abilityService.getAllByUserId(userId);
	}
	
	@PostMapping("/api/cvs/abilities/add")
	public ResponseEntity<?> add(@Valid @RequestBody Ability ability) {
		return ResponseEntity.ok(this.abilityService.add(ability));
	}
	@PostMapping("/api/cvs/abilities/addList")
	public ResponseEntity<?> add(@RequestBody List<Ability> ability) {
		return ResponseEntity.ok(this.abilityService.add(ability));
	}
	
									//EDUCATİONS CONTROLLER	

	@GetMapping("/api/cvs/educations/getall")
	public DataResult<List<Education>> getAllEducations() {
		return this.educationService.getAll();
	}
	
	@GetMapping("/api/cvs/educations/getbyuserid")
	public DataResult<List<Education>> getEducationsByUserId(int userId) {
		return this.educationService.getAllByUserIdOrderByGraduationDateDesc(userId);
	}
	
	@PostMapping("/api/cvs/eduations/add")
	public ResponseEntity<?> add(@Valid @RequestBody Education education) {
		return ResponseEntity.ok(this.educationService.add(education));
	}
	
								//EXPERIENCES CONTROLLER
	
	@GetMapping("/api/cvs/experiences/getall")
	public DataResult<List<Experience>> getAllExperiences() {
		return this.experienceService.getAll();
	}
	
	@GetMapping("/api/cvs/experiences/getbyuserid")
	public DataResult<List<Experience>> getExperiencesByUserId(int userId) {
		return this.experienceService.getAllByUserIdOrderByQuitDate(userId);
	}
	
	@PostMapping("/api/cvs/experiences/add")
	public ResponseEntity<?> add(@Valid @RequestBody Experience experience) {
		return ResponseEntity.ok(this.experienceService.add(experience));
	}
	
								//IMAGES CONTROLLER
	
	@GetMapping("/cloudinary/images/getall")
	public ResponseEntity<List<Image>> getAllImages(){
		
		List<Image> list  = this.imageService.getAll().getData();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("/cloudinary/images/getallbyuserid")
	public DataResult<List<Image>> getAllImagesByUserId (int userId){
		
		return this.imageService.getAllByUserId(userId);
	}
	
	
	@PostMapping("/cloudinary/images/upload")
	public ResponseEntity<?> upload (@ModelAttribute Image image,MultipartFile multipartFile) throws IOException{
		
		BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
		if(bufferedImage == null) {
			
			return new ResponseEntity<>("Resim yüklenemedi",HttpStatus.BAD_REQUEST);
		}
		
		Map uploadResult= cloudinaryService.upload(multipartFile);
		
		image.setName((String)uploadResult.get("original_filename"));
		image.setImageUrl((String)uploadResult.get("url"));
		image.setImageId((String)uploadResult.get("public_id"));
	
		
		this.imageService.add(image);
		return new ResponseEntity<>("Resim yüklendi",HttpStatus.OK);
	}
	
										//LANGUAGES CONTROLLER
	
	@GetMapping("/api/cvs/languages/getall")
	public DataResult<List<Language>> getAllLanguages() {
		return this.languageService.getAll();
	}
	
	@GetMapping("/api/cvs/languages/getallbyuserid")
	public DataResult<List<Language>> getAllLanguagesByUserId(int userId) {
		return this.languageService.getAllByUserId(userId);
	}
	
	
	@PostMapping("/api/cvs/languages/add")
	public ResponseEntity<?> add(@Valid @RequestBody Language language) {
		return ResponseEntity.ok(this.languageService.add(language));
	}
	
								//SINGLE INFORMATION CONTROLLER
	
	@GetMapping("/api/cvs/singleinfos/getall")
	public DataResult<List<SingleInformation>> getAllSingleInformations(){
		return this.singleInformationService.getAll();
	}
	
	@GetMapping("/api/cvs/singleinfos/getbyuserid")
	public DataResult<SingleInformation> getSingleInformationsByUserId(int userId){
		return this.singleInformationService.getByUserId(userId);
	}
	
	@PostMapping("/api/cvs/singleinfos/add")
	public ResponseEntity<?> add(@Valid @RequestBody SingleInformation singleInformation) {
		return ResponseEntity.ok(this.singleInformationService.add(singleInformation));
	}
	
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
		Map<String,String> validationErrors = new HashMap<String, String>();
		 for(FieldError fieldError :  exceptions.getBindingResult().getFieldErrors() ) {
			 validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		 }
		 
		 ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors,"Doğrulama hataları");
		 return errors;
	}

}
