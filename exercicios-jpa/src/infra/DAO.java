package infra;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;



public class DAO<E> {
	
	private static EntityManagerFactory emf; // quando há um bloco estatico deve ser inicializada em um bloco estatico
	private EntityManager em;
	private Class<E> classe;
	
	static {
		try {
			emf = Persistence.createEntityManagerFactory("exercicio-jpa");
		} catch(Exception e) {
			//logar -> log4j
		}
		
	}
	
	public DAO() {
		this(null);
	}
	
	public DAO(Class<E> classe) {
		this.classe = classe;
		em = emf.createEntityManager();
		
	}
	
	public DAO<E> abrirT() {
		em.getTransaction().begin();
		return this;
		
	}
	
	public DAO<E> fecharT(){
		em.getTransaction().commit();
		return this;
	}
	
	public DAO<E> incluir(E entidade){
		em.persist(entidade);
		return this;
		
	}
	
	// incluir atomico é que faz tudo ou nada 
	
	public DAO<E> incluirAtomico(E entidade){
		return this.abrirT().incluir(entidade).fecharT();
		
	}
	
	public List<E> obterTodos(){
		return this.obterTodos(10, 0);
	}
	
	// a qtde e o inicio EX numero 4, 2... o deslocamento e a quantidade depois 3 4 registros depois do inicio
	
	public List<E> obterTodos(int qtde, int deslocamento){
		if(classe == null) {
			throw new UnsupportedOperationException("Classe nula.");
			
		}
		
		String jpql = "select e from " + classe.getName() + " e";
		TypedQuery<E> query = em.createQuery(jpql, classe);
		query.setMaxResults(qtde);
		query.setFirstResult(deslocamento);
		return query.getResultList();
	
	}
	
	public void fechar() {
		em.close();
		
		
	}
	
		
}
