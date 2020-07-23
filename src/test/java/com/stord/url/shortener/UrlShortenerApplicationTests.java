package com.stord.url.shortener;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UrlShortenerApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void testConvertToLongURL()
	{
		Base62Converter converter = new Base62Converter();
		long ret = converter.convertToLongURL("a");	
		assert(ret == 0);
		ret = converter.convertToLongURL("ahvdhgdgdhghggherhgghgsdvvsdvvh");
		assert(ret > 0);
		ret = converter.convertToLongURL("-thrioeoeo.yjjifo7r89ri");
		assert(ret > 0);
		ret = converter.convertToLongURL("wwww.internetusers.com/newshortner");
		assert(ret > 0);
		ret = converter.convertToLongURL("hhtp:/google.com/testmynewwebapplication/test/");
		assert(ret > 0);
		ret = converter.convertToLongURL("sLLs");
		assert(ret == 4434444);
		ret = converter.convertToLongURL("cd");
		assert(ret == 127);
	}
	
	@Test
	void testConvertTD()
	{
		Base62Converter converter = new Base62Converter();
		String ret = converter.convertToShortURL(1);
		assert(ret != null);
		ret = converter.convertToShortURL(9999999);
		assert(ret != null);
		ret = converter.convertToShortURL(4434444);
		assert(ret.equals("sLLs"));
		ret = converter.convertToShortURL(127);
		assert(ret.equals("cd"));
		
	}
	


}
