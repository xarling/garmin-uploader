package codist.garmin.uploader.rest;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import codist.garmin.uploader.rest.model.FitFile;
import codist.garmin.uploader.service.FitService;
import codist.garmin.uploader.util.DozerCollectionMapper;



@Controller
@RequestMapping("/api/fit")
public class FitFileController {
	
	@Autowired
	private FitService fitService;
	
	@Autowired
	private Mapper mapper;
	
	@RequestMapping(method=RequestMethod.GET)
    public @ResponseBody List<FitFile> getFitFiles() {
        return DozerCollectionMapper.map(mapper, fitService.getAll(), FitFile.class);
    }
	
	@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
    public @ResponseBody FitFile save(@PathVariable Integer id, @RequestBody final FitFile fitFile) {
		final codist.garmin.uploader.model.FitFile file = fitService.save(mapper.map(fitFile, codist.garmin.uploader.model.FitFile.class));
        return mapper.map(file, FitFile.class);
    } 

}
