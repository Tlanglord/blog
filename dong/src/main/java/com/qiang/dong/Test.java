package com.qiang.dong;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Hello world!
 *
 */
public class Test 
{
    public static void main( String[] args )
    {
//    	Gson gson = new Gson();
//    	Person<String> p = new Person<String>();
//    	p.setAge(22);
//    	p.setName("Curry");
//    	//persons.add(p);//将Person对象加入到gson中
//    	String str = gson.toJson(p);
//    	
//    	System.out.println(str);
//    	
//    	Person<?> p1 = gson.fromJson(str, p.getClass());
//    	
//    	System.out.println(p1.getName());
//    	System.out.println(p1.getAge());
    	
    	Gson gson = new Gson();
		Foo<List<String>> foo = new Foo<List<String>>();
		String json = gson.toJson(foo); // May not serialize foo.value correctly

		
		//Foo<List<String>> foo1 = gson.fromJson(json,fooType);
		
		System.out.println(new TypeToken<Foo<List<String>>>(){}.getClass());
    	
    }
}
