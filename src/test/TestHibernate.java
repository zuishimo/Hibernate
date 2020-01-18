package test;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;


import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import entity.Product;
import javassist.expr.NewArray;

public class TestHibernate {
	
	
	public static void main(String[] args) {
		//加载hibernate的核心配置文件：hibernate.cfg.xml
		Configuration configuration = new Configuration().configure();
		//创建一个sessionFactory对象，用于建立会话
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		//建立会话
		Session s = sessionFactory.openSession();
		//手动开启会话
		s.beginTransaction();
		String name = "iphone1";
		//CriteriaBuilder初始化
		CriteriaBuilder cb = s.getCriteriaBuilder();
		//获取criteriaquery对象
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		//指定根条件
		Root<Product> root = cq.from(Product.class);
		//按条件查询
		Predicate id = cb.equal(root.get("id"), 50);
		Predicate price = cb.between(root.get("price"), 3000, 10000);
		//将查询条件设置到where方法中
		cq.where(cb.and(price));
		
		List<Product> list = s.createQuery(cq).list();
		for (Product product : list) {
			System.out.println(product.getName());
		}
		//提交事务
		s.getTransaction().commit();
		//关闭会话
		sessionFactory.close();
		s.close();	
	}
}
