package br.com.lo.dimed.poa.util;

import java.util.Arrays;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class Util {

	public static MappingJackson2HttpMessageConverter getMessageConverterHtml() {
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_HTML));
		return mappingJackson2HttpMessageConverter;
	}
}
