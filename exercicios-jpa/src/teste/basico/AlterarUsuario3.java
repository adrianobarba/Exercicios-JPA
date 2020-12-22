package teste.basico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Usuario;

public class AlterarUsuario3 {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicios-jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Usuario usuario = em.find(Usuario.class, 7L);
		em.detach(usuario);// esse metodo desassocia a alteração com a sincronia do banco
		usuario.setNome("Adriano alterado 3");
		em.merge(usuario); // quando coloco o merge ele atualiza o usuario de toda forma
		
		em.getTransaction().commit();
	
		em.close();
		emf.close();
		

	}

}
