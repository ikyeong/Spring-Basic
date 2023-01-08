package memo.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import memo.domain.Memo;

import java.util.List;
import java.util.Optional;

public class JpaMemoRepository implements MemoRepository{

    private final EntityManager em;

    public JpaMemoRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Memo save(Memo memo) {
        em.persist(memo);
        return memo;
    }

    @Override
    public void deleteById(Long id) {
        String query = "delete from Memo m where m.id = :id";
        em.createQuery(query).setParameter("id",id).executeUpdate();
    }

    @Override
    public Optional<Memo> findById(Long id) {
        Memo memo = em.find(Memo.class, id);
        return Optional.ofNullable(memo);
    }

    @Override
    public List<Memo> findAll() {
        return em.createQuery("select m from Memo m",Memo.class)
                .getResultList();
    }
}
