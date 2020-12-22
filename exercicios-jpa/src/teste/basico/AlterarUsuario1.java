package teste.basico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Usuario;

public class AlterarUsuario1 {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicios-jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Usuario usuario = em.find(Usuario.class, 7L);
		usuario.setNome("Adriano");
		usuario.setEmail("adriano@lanche.com.br");
		
//		System.out.println(usuario.getEmail()); Consultar usuario 7
		
		em.merge(usuario);
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();

	}
}
