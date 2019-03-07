package cn.itcast.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.itcast.entity.User;
import cn.itcast.uitls.HibernateUtils;

public class HibernateQueryData {
	@Test
	public void testQuery() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = HibernateUtils.getSessionObject();
			transaction = session.beginTransaction();
			
			//1、创建query对象
			Query< User> query = session.createQuery("from User");
			
			//2、调用query对象里的方法得到结构
			List<User> list = query.list();
			for (User user : list) {
				System.out.println(user);
			}
			
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}finally {
			session.close();
			sessionFactory.close();
		}
	}
}
