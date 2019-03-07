package cn.itcast.hibernate;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.itcast.entity.User;
import cn.itcast.uitls.HibernateUtils;

public class HibernateSQLQuery {
	@Test
	public void testSQLQuery() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = HibernateUtils.getSessionObject();
			transaction = session.beginTransaction();
			
			//1、创建query对象
			SQLQuery<Object[]> sqlQuery = session.createSQLQuery("select * from t_user");
			
			
			//2、调用query对象里的方法得到结构
			@SuppressWarnings("deprecation")
			List<Object[]> list = sqlQuery.list();
			for (Object[] objects : list) {
				System.out.println(Arrays.toString(objects));
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
