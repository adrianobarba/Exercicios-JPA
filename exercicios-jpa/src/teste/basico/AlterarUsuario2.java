package teste.basico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Usuario;

public class AlterarUsuario2 {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicios-jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Usuario usuario = em.find(Usuario.class, 7L);
		usuario.setNome("Adriano");
		//usuario.setEmail("adriano@lanche.com.br");

		//em.merge(usuario); Se n�o usar o merge o jpa faz uma sincroniza��o com o banco
		// isso acontece pq o objeto esta em estado gerenciado dentro do metodo transacional
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();

	}

}
