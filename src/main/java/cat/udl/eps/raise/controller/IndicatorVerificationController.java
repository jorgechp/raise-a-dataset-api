package cat.udl.eps.raise.controller;

import cat.udl.eps.raise.projection.VerificationRequestDTO;
import cat.udl.eps.raise.service.IndicatorVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@BasePathAwareController
@RestController
public class IndicatorVerificationController {
    @Autowired
    private IndicatorVerificationService verificationService;

    @PostMapping("/verify")
    public ResponseEntity<?> verifyDataset(@RequestBody VerificationRequestDTO request) {
        verificationService.sendVerificationRequest(request);
        return ResponseEntity.ok("Verification request received.");
    }
}
