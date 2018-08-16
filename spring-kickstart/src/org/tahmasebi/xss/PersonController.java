package org.tahmasebi.xss;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/personService")
public class PersonController {

  @RequestMapping( value= "/test" )
  private void getTest2(@RequestParam String name) {

      System.out.println(name);

//      System.out.println( StringEscapeUtils.escapeHtml(name) );

  }

}