package com.greco.rest;

import com.greco.model.SolarPanel;
import com.greco.model.projection.IProjectable;
import com.greco.model.projection.Projection;
import com.greco.service.SolarPanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("solarPanel")
public class SolarPanelRestController {
    @Autowired
    SolarPanelService solarPanelService;

    @GetMapping("{id}")
    public SolarPanel /*IProjectable*/ byId(@PathVariable("id") Long id) {
       // return Projection.convertSingle(solarPanelService.findById(id), "solarPanel");
        //return Projection.convertSingle(mockSolarPanel(), "solarPanel");
        return mockSolarPanel();
    }

    @PutMapping
    public SolarPanel /*IProjectable*/ update(@RequestBody SolarPanel solarPanel) {
        return mockSolarPanel();
    }

    @PostMapping
    public SolarPanel /*IProjectable*/ insert(@RequestBody SolarPanel solarPanel) {
        return solarPanel;
    }

    private SolarPanel mockSolarPanel() {
        SolarPanel solarPanel = new SolarPanel();
        solarPanel.setCommissioningDate(new Date());
        solarPanel.setElectricalCapacity(19.5);
        solarPanel.setOrientation("south");
        solarPanel.setInclination("45");
        solarPanel.setLat("41.3818");
        solarPanel.setLon("2.1685");
        solarPanel.setMunicipality("Barcelona");
        solarPanel.setPostcode("08041");
        solarPanel.setTechnologyUsed("monocrystalline silicon");
        return solarPanel;
    }
}
