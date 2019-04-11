package vaalikone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persist.Ehdokkaat;
import persist.Vastaukset;
import persist.VastauksetPK;

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

		String idstring = request.getParameter("candidateid");
		int idc = Integer.parseInt(idstring.trim());
		
		//String idqstring = request.getParameter("questionid");
		//int idq = Integer.parseInt(idqstring.trim());
		
		List list = new ArrayList();
		list.add(idc);
	//	list.add(idq);
		
		EntityManagerFactory emf = null;
		EntityManager em = null;
		
		try {
		
		emf = Persistence.createEntityManagerFactory("vaalikones");
		em = emf.createEntityManager();
		
		} catch (Exception e) {
			response.getWriter().println("EMF + EM ei onnistu.");
		}
			
		/* Ehdokkaat ehdokas = new Ehdokkaat();
		
		ehdokas.setEhdokasId(Integer.parseInt(request.getParameter("id")));
		
		ehdokas.setEtunimi("Uusi");
		
		ehdokas.setSukunimi("Nimi");
		
		// em.getTransaction().begin();
		
		em.persist(ehdokas);
		
		em.getTransaction().commit();
		
		em.close();
		
		// response.getWriter().print(ehdokas);
		
//		String fName = request.getParameter("fName");
		
	//	Kysymykset kysymys = new Kysymykset();
		
	//	kysymys.setKysymys("kysymys");
		
	//	e.setEtunimi(fName);
		
		} catch (Exception e) {
			
		//	em.getTransaction().rollback();
			
		//	em.close();
			
			response.getWriter().print("Couldn't save object to database.");
			
			response.getWriter().print(e);
	
		}
	*/	
		try {
			Ehdokkaat ehdokas = new Ehdokkaat(idc);
			ehdokas.setEhdokasId(Integer.parseInt(request.getParameter("candidateid")));
			ehdokas.setSukunimi(request.getParameter("candidatelname"));
			ehdokas.setEtunimi(request.getParameter("candidatefname"));
			ehdokas.setPuolue(request.getParameter("party"));
			ehdokas.setKotipaikkakunta(request.getParameter("homecity"));
			ehdokas.setIka(Integer.parseInt(request.getParameter("age")));
			ehdokas.setMiksiEduskuntaan(request.getParameter("whytoparliament"));
			ehdokas.setMitaAsioitaHaluatEdistaa(request.getParameter("whatarethethingsyouwanttoaffect"));
			ehdokas.setAmmatti(request.getParameter("profession"));
		//	Vastaukset answer = em.find(Vastaukset.class, answerPK);
			
			em.getTransaction().begin();
			em.persist(ehdokas);
			em.getTransaction().commit();
			em.close();
			
			response.getWriter().println("Candidate added to database!");
		} catch (Exception e) {
			response.getWriter().println();
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
