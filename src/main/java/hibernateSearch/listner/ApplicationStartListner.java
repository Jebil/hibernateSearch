package hibernateSearch.listner;

import java.util.concurrent.Executors;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class ApplicationStartListner
		implements ApplicationListener<ContextRefreshedEvent> {
	
	private static boolean flag = true;
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (flag) {
			Session session = sessionFactory.getCurrentSession();
			System.out.println(
					"onApplicationEvent ContextRefreshedEvent ***********************");
			flag = false;
			Executors.newSingleThreadExecutor().execute(new Runnable() {
				@Override
				public void run() {
					FullTextSession fullTextSession = Search
							.getFullTextSession(session);
					try {
						fullTextSession.createIndexer().startAndWait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}

	}

}
