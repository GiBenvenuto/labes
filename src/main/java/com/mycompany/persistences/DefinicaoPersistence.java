/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.persistences;

import com.mycompany.configs.HibernateUtil;
import com.mycompany.model.Definicao;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.logging.Level;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Gi Benvenuto
 */
public class DefinicaoPersistence {
    
    public static boolean save(Definicao definicao) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        boolean commited = false;
        Transaction t = null;
        try {
            t = session.beginTransaction();
            session.save(definicao);
            t.commit();
            commited = true;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "ERRO: [{0}]", e.getMessage());
            if (t != null && !t.wasCommitted()) {
                t.rollback();
            }
        } finally {
            session.close();
        }
        return commited;
    }
    
}
