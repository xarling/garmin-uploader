package codist.garmin.uploader.fit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/api/fit")
public class FitFileController {
	
	@Autowired
	private FitService fitService;
	
	@RequestMapping(method=RequestMethod.GET)
    public @ResponseBody Iterable<FitFile> getFitFiles() {
        return fitService.getAll();
    }

}
