package vaalikone;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persist.Ehdokkaat;

/**
 * Servlet implementation class SaveCandidate
 */
@WebServlet(
	    name = "AddCandidate",
	    urlPatterns = {"/AddCandidate"}
	)
public class AddCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCandidate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		EntityManagerFactory emf = null;
		EntityManager em = null;
		
		try {
		
		emf = Persistence.createEntityManagerFactory("vaalikones");
		em = emf.createEntityManager();
			
		Ehdokkaat ehdokas = new Ehdokkaat();
		
		ehdokas.setEtunimi("Uusi");
		
		ehdokas.setSukunimi("Nimi");
		
		em.getTransaction().begin();
		
		em.persist(ehdokas);
		
		em.getTransaction().commit();
		
		em.close();
		
		response.getWriter().print(ehdokas);
		
		String fName = request.getParameter("fName");
		
	//	Kysymykset kysymys = new Kysymykset();
		
	//	kysymys.setKysymys("kysymys");
		
	//	e.setEtunimi(fName);
		
		} catch (Exception e) {
			
			em.getTransaction().rollback();
			
			em.close();
			
			response.getWriter().print("Couldn't save object to database.");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
