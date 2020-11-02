package cart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cart.services.OrderQuantityReportGenerator;
import cart.services.OrderReportGenerator;

@RestController
@RequestMapping("/api")
public class ReportGenerationController {

    @Autowired
    private OrderReportGenerator orderReportGenerator;
    @Autowired
    private OrderQuantityReportGenerator orderQuantityReportGenerator;

    @GetMapping("/first-report")
    public Object getReport() {
        return orderReportGenerator.generate();
    }

    @GetMapping("/second-report")
    public Object getSecondReport() {
        return orderQuantityReportGenerator.generate();
    }
}
