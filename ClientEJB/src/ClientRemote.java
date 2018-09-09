import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import metier.BanqueRemote;
import metier.entities.Compte;

public class ClientRemote {

	public static void main(String[] args) {
		
		try {
			Context ctx= new InitialContext();
			String appName="BanqueEAR";
			String moduleName="BanqueEJB";
			String beanName="BK";
//			String remoteInterface="metier.BanqueRemote";
			String remoteInterface=BanqueRemote.class.getName();
			String name="ejb:"+appName+"/"+moduleName+"/"+beanName+"!"+remoteInterface;
			BanqueRemote proxy = (BanqueRemote) ctx.lookup(name);
			
			proxy.addCompte(new Compte());
			proxy.addCompte(new Compte());
			proxy.addCompte(new Compte());
			proxy.addCompte(new Compte());
			
			Compte cpt1=proxy.getCompte(1L);
			proxy.verser(1L, 4000);
			
			Compte cpt2 = proxy.getCompte(2L);
			proxy.virement(1L, 2L, 2000);
			
			List<Compte> list = proxy.listCompte();
			for(Compte compte:list) {
				System.out.println(compte.getSolde());
			}
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
