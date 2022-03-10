package ga.baocai.adal.web.controller;

import ga.baocai.adal.web.common.CommonResponse;
import ga.baocai.adal.web.service.SysMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sysMachine")
public class SysMachineController {
    @Autowired
    private SysMachineService sysMachineService;

    @GetMapping("/query")
    public CommonResponse query() {
        return CommonResponse.ofSuccess(sysMachineService.query());
    }
}
