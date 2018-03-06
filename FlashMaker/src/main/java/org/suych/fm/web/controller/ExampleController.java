package org.suych.fm.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.suych.fm.utils.IdBuilder;
import org.suych.fm.web.model.ExampleModel;
import org.suych.fm.web.service.IExampleService;

@RestController
@RequestMapping("/example")
public class ExampleController {

	@Autowired
	private IExampleService exampleSerivceImpl;

	/**
	 * 根据单个ID查询
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getExampleById", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public String getExampleById(HttpServletRequest request) throws Exception {
		String example1 = "18DF418D9E0D4020AAE2A431619D5782";
		List<ExampleModel> examples = exampleSerivceImpl.getExampleById(example1);
		examples.forEach((n) -> {
			System.out.println(n.getExample1());
			System.out.println(n.getExample2());
			System.out.println(n.getExample3());
			System.out.println(n.getExample4());
		});
		return null;
	}

	/**
	 * 根据批量ID查询
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getExampleByIds", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public String getExampleByIds(HttpServletRequest request) throws Exception {
		List<String> ids = new ArrayList<String>() {
			{
				add("18DF418D9E0D4020AAE2A431619D5782");
				add("14DADCAF4F8A43FB9084559F85B2B92F");
				add("96C862148E5842BAAB2AAB61E9CFB38F");
				add("99FC958F78D84F0BA55A71654030D244");
				add("2EB832D00CFA4119B47416080E4F7124");
			}
		};
		List<ExampleModel> examples = exampleSerivceImpl.getExampleByIds(ids);
		examples.forEach((n) -> {
			System.out.println(n.getExample1());
			System.out.println(n.getExample2());
			System.out.println(n.getExample3());
			System.out.println(n.getExample4());
		});
		return null;
	}

	/**
	 * 新增
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertExample", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public String insertExample(HttpServletRequest request) throws Exception {
		ExampleModel example = new ExampleModel();
		example.setExample1(IdBuilder.newId());
		example.setExample2(11);
		example.setExample3(new Date());
		example.setExample4("{'json':'10'}");
		exampleSerivceImpl.insertExample(example);
		return null;
	}

	/**
	 * 根据ID修改
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateExampleById", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public String updateExampleById(HttpServletRequest request) throws Exception {
		String id = "A249C71CCDAA4625A51215E642874D2E";
		ExampleModel example = new ExampleModel();
		example.setExample2(12);
		example.setExample3(new Date());
		example.setExample4("{'json':'12'}");
		exampleSerivceImpl.updateExampleById(example, id);
		return null;
	}

	/**
	 * 根据ID删除
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteExampleById", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public String deleteExampleById(HttpServletRequest request) throws Exception {
		String id = "A249C71CCDAA4625A51215E642874D2E";
		exampleSerivceImpl.deleteExampleById(id);
		return null;
	}

	/**
	 * 批量新增
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/batchInsertExample", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public String batchInsertExample(HttpServletRequest request) throws Exception {
		List<ExampleModel> examples = new ArrayList<ExampleModel>() {
			{
				ExampleModel example1 = new ExampleModel();
				example1.setExample1(IdBuilder.newId());
				example1.setExample2(13);
				example1.setExample3(new Date());
				example1.setExample4("{'json':'13'}");

				ExampleModel example2 = new ExampleModel();
				example2.setExample1(IdBuilder.newId());
				example2.setExample2(14);
				example2.setExample3(new Date());
				example2.setExample4("{'json':'14'}");

				add(example1);
				add(example2);
			}
		};
		exampleSerivceImpl.batchInsertExample(examples);
		return null;
	}

	/**
	 * 批量删除
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/batchDeleteExample", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public String batchDeleteExample(HttpServletRequest request) throws Exception {
		List<String> ids = new ArrayList<String>() {
			{
				add("55DABD1080D84690A8C21B86AB882C90");
				add("E6CAF9F4D9BD41B2B61BE1FF510EF5C2");
			}
		};
		exampleSerivceImpl.batchDeleteExample(ids);
		return null;
	}

}
