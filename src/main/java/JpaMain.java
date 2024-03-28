import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team teamA = new Team();
            teamA.setName("TeamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("TeamB");
            em.persist(teamB);

            Member member = new Member();
            member.setUsername("관리자");
            member.setAge(0);
            member.setTeam(teamA);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("관리자");
            member.setAge(0);
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("관리자");
            member.setAge(0);
            member3.setTeam(teamB);
            em.persist(member3);


            //flush
            int rstCnt = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            System.out.println("findMember = " + findMember.getAge());

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
