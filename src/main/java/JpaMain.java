import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("관리자");
            member.setAge(11);
            member.setTeam(team);

            em.persist(member);


            em.flush();
            em.clear();

            String q = "select NULLIF (m.username, '관리자') from Member m";
            List<String> query = em.createQuery(q, String.class)
                            .getResultList();

            for (String s : query) {
                System.out.println("s = " + s);
            }
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
