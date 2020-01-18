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
		//����hibernate�ĺ��������ļ���hibernate.cfg.xml
		Configuration configuration = new Configuration().configure();
		//����һ��sessionFactory�������ڽ����Ự
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		//�����Ự
		Session s = sessionFactory.openSession();
		//�ֶ������Ự
		s.beginTransaction();
		String name = "iphone1";
		//CriteriaBuilder��ʼ��
		CriteriaBuilder cb = s.getCriteriaBuilder();
		//��ȡcriteriaquery����
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		//ָ��������
		Root<Product> root = cq.from(Product.class);
		//��������ѯ
		Predicate id = cb.equal(root.get("id"), 50);
		Predicate price = cb.between(root.get("price"), 3000, 10000);
		//����ѯ�������õ�where������
		cq.where(cb.and(price));
		
		List<Product> list = s.createQuery(cq).list();
		for (Product product : list) {
			System.out.println(product.getName());
		}
		//�ύ����
		s.getTransaction().commit();
		//�رջỰ
		sessionFactory.close();
		s.close();	
	}
}
