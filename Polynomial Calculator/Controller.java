import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Model m_model;
    private View m_view;

    Controller(Model model, View view) {
        m_model = model;
        m_view = view;

        view.addAddListener(new AddListener());
        view.addSListener(new SListener());
        view.addMulListener(new MulListener());
        view.addDivListener(new DivListener());
        view.addDerivListener(new DerivListener());
        view.addIntListener(new IntListener());
        view.addClearListener(new ClearListener());


    }

    class AddListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            String userInput1 = "";
            String userInput2="";
            userInput1 = m_view.getUserInput_1();
            userInput2= m_view.getUserInput_2();
            Polinom rez=new Polinom();
            rez=Model.aduna(new Polinom(userInput1), new Polinom(userInput2));
            m_view.setRezultat(rez);

        }
    }

    class SListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            String userInput1 = "";
            String userInput2="";
            userInput1 = m_view.getUserInput_1();
            userInput2= m_view.getUserInput_2();
            Polinom rez=new Polinom();
            rez=Model.scade(new Polinom(userInput1), new Polinom(userInput2));
            m_view.setRezultat(rez);

        }
    }

    class MulListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            String userInput1 = "";
            String userInput2="";
            userInput1 = m_view.getUserInput_1();
            userInput2= m_view.getUserInput_2();
            Polinom rez=new Polinom();
            rez=Model.inmulteste(new Polinom(userInput1), new Polinom(userInput2));
            m_view.setRezultat(rez);

        }
    }

    class DivListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            String userInput1 = "";
            String userInput2="";
            userInput1 = m_view.getUserInput_1();
            userInput2= m_view.getUserInput_2();
            Polinom rez=new Polinom();
            Polinom cat=new Polinom();
            Polinom rest=new Polinom();
            Model.imparte(cat, rest,new Polinom(userInput1), new Polinom(userInput2));
            m_view.setRezultat(cat);
            m_view.setRest(rest);

        }
    }

    class DerivListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            String userInput1 = "";
            String userInput2="";
            userInput1 = m_view.getUserInput_1();
            userInput2= m_view.getUserInput_2();
            Polinom rez=new Polinom();
            rez=Model.deriveaza(new Polinom(userInput1));
            m_view.setRezultat(rez);

        }
    }

    class IntListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            String userInput1 = "";
            String userInput2="";
            userInput1 = m_view.getUserInput_1();
            userInput2= m_view.getUserInput_2();
            Polinom rez=new Polinom();
            rez=Model.integreaza(new Polinom(userInput1));
            m_view.setRezultat(rez);

        }
    }

    class ClearListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            m_view.reset();

        }
    }





}
