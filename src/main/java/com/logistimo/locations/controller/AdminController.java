package com.logistimo.locations.controller;

import com.logistimo.locations.LocationLoader;
import com.logistimo.locations.PlaceLoader;
import com.logistimo.locations.cache.LocationCacheUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by kumargaurav on 03/03/17.
 */
@RestController
@RequestMapping(path = "/admins")
public class AdminController {

  private static final Logger log = LoggerFactory.getLogger(AdminController.class);

  @Resource
  LocationCacheUtil lcCacheManager;

  @Resource
  LocationLoader lloader;

  @Resource
  PlaceLoader ploader;

  @RequestMapping(path = "/reloadcache",method = RequestMethod.GET)
  public @ResponseBody
  String reloadCache() {
    lcCacheManager.reloadCache();
    log.info("locations caches reloaded");
    return "Cache reloaded!!";
  }

  @RequestMapping(path = "/clearcache",method = RequestMethod.GET)//check camelcase is standard or not
  public @ResponseBody String clearCache() {
    lcCacheManager.burstAllCache();
    log.info("locations caches cleared");
    return "Cache cleared!!";
  }

  @RequestMapping(path = "/getcachedentity", method = RequestMethod.GET)
  public
  @ResponseBody
  Object getCacheEntity(@RequestParam String type, @RequestParam String key) {
    log.info("getting cached entity of type {} with key {}", type, key);
    return lcCacheManager.getCacheObject(type, key);
  }

  @RequestMapping(path = "/loadlocations", method = RequestMethod.GET)
  public
  @ResponseBody
  String initLocations() {
    try {
      lloader.load();
      Thread.sleep(10000l);
      ploader.load();
    } catch (Exception e) {
      log.error("Error with location entities init", e.getMessage());
    }
    return "location entities initialized!!";
  }
}
