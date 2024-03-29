package interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import zoneGeographique.Jeu;

public class FenetreSaisie extends JFrame implements ActionListener {

	private int n, m, nbSorties, nbSacArgent, nbCh, nbObstacles;

	private JPanel saisie, saisieCenter;
	private JButton button;
	private JTextField ligneText, colonneText, nbcText, nbSortieText, nbSacArgentText, nbObstaclesText, nomJText;
	private JLabel errorLabel;

	private boolean garderJoueurs;
	private String nomJ1, nomJ2;

	public FenetreSaisie(boolean garderJoueurs) {

		this.garderJoueurs = garderJoueurs;
		nomJ1 = "";
		nomJ2 = "";

		saisie = new JPanel(new BorderLayout());

		JPanel infoPanel = new JPanel();
		JLabel infoLabel = new JLabel("Saisir les informations necessaire pour commencer le jeu");
		infoLabel.setForeground(Color.blue.darker().darker());
		infoLabel.setFont(new Font("Arial", Font.BOLD, 17));
		infoPanel.add(infoLabel);

		saisie.add(infoPanel, BorderLayout.NORTH);

		JLabel tailleGrilleLabel = new JLabel("Saisir la taille de la grille :");
		tailleGrilleLabel.setBounds(40, 20, 400, 25);
		tailleGrilleLabel.setFont(new Font("Arial", Font.BOLD, 14));

		JLabel ligneLabel = new JLabel("Nombre de lignes de la zone geographique   (12-20)");
		ligneLabel.setBounds(40, 50, 540, 25);
		ligneLabel.setFont(new Font("Arial", Font.BOLD, 14));

		JLabel colonneLabel = new JLabel("Nombre de colonnes de la zone geographique   (12-30)");
		colonneLabel.setBounds(40, 80, 540, 25);
		colonneLabel.setFont(new Font("Arial", Font.BOLD, 14));

		ligneText = new JTextField(20);
		ligneText.setBounds(550, 50, 100, 25);
		colonneText = new JTextField(20);
		colonneText.setBounds(550, 80, 100, 25);

		JLabel nbcLabel = new JLabel("Saisir le nombre de robots / intrus   (1-4)"); // nbre de robots/intus
		nbcLabel.setBounds(40, 140, 540, 25);
		nbcLabel.setFont(new Font("Arial", Font.BOLD, 14));

		nbcText = new JTextField(20);
		nbcText.setBounds(550, 140, 100, 25);

		JLabel nbSortieLabel = new JLabel("Saisir le nombre de sorties de la zone (1-5)");
		nbSortieLabel.setBounds(40, 170, 540, 25);
		nbSortieLabel.setFont(new Font("Arial", Font.BOLD, 14));

		nbSortieText = new JTextField(20);
		nbSortieText.setBounds(550, 170, 100, 25);

		JLabel nbSacArgentLabel = new JLabel("Saisir le nombre de sacs d'argent");
		nbSacArgentLabel.setBounds(40, 200, 540, 25);
		nbSacArgentLabel.setFont(new Font("Arial", Font.BOLD, 14));

		nbSacArgentText = new JTextField(20);
		nbSacArgentText.setBounds(550, 200, 100, 25);

		JLabel nbObstaclesLabel = new JLabel("Saisir le nombre de obstacles de la zone (1-5)");
		nbObstaclesLabel.setBounds(40, 240, 540, 25);
		nbObstaclesLabel.setFont(new Font("Arial", Font.BOLD, 14));

		nbObstaclesText = new JTextField(20);
		nbObstaclesText.setBounds(550, 240, 100, 25);

		errorLabel = new JLabel("");
		errorLabel.setBounds(40, 400, 750, 25);
		errorLabel.setForeground(Color.red.darker());
		errorLabel.setFont(new Font("Arial", Font.BOLD, 12));

		button = new JButton("Suivant");
		button.setFont(new Font("Aerial", Font.BOLD, 13));
		button.setBackground(new Color(32, 74, 135));
		button.setForeground(Color.WHITE);
		button.setBounds(650, 450, 150, 30);
		button.addActionListener(this);

		saisieCenter = new JPanel(null);
		saisieCenter.add(tailleGrilleLabel);
		saisieCenter.add(ligneLabel);
		saisieCenter.add(ligneText);
		saisieCenter.add(colonneLabel);
		saisieCenter.add(colonneText);

		saisieCenter.add(nbcLabel);
		saisieCenter.add(nbcText);

		saisieCenter.add(nbSortieLabel);
		saisieCenter.add(nbSortieText);
		saisieCenter.add(nbSacArgentLabel);
		saisieCenter.add(nbSacArgentText);

		saisieCenter.add(nbObstaclesLabel);
		saisieCenter.add(nbObstaclesText);

		saisieCenter.add(errorLabel);
		saisieCenter.add(button);

		saisie.add(saisieCenter, BorderLayout.CENTER);

		add(saisie);

		setSize(900, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Jeu multi-joueurs pour la surveillance d une zone geographique");
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {

			String nbLignesString = ligneText.getText();
			String nbColonnesString = colonneText.getText();
			String nbCharString = nbcText.getText();
			String nbSortieString = nbSortieText.getText();
			String nbSacString = nbSacArgentText.getText();
			String nbObstaclesString = nbObstaclesText.getText();

			try {
				// peut generer une exception
				n = Integer.parseInt(nbLignesString);
				m = Integer.parseInt(nbColonnesString);
				nbCh = Integer.parseInt(nbCharString);
				nbSorties = Integer.parseInt(nbSortieString);
				nbSacArgent = Integer.parseInt(nbSacString);
				nbObstacles = Integer.parseInt(nbObstaclesString);

				// 10 <= n,m <= 30 ; 0 < nbch <=4 ; 0 < nbSorties < 6 ;
				if ((n < 12) || (n > 20) || (m < 12) || (m > 30) || (nbCh <= 0) || (nbCh > 4) || (nbSorties < 1)
						|| (nbSorties > 5) || (nbObstacles < 1) || (nbObstacles > 5)) {
					errorLabel.setText("Erreur lors de la saisie, reessayez!");
				} else if ((nbCh == 1) && (nbSacArgent != 3) && (nbSacArgent != 2)) {
					errorLabel.setText("Si le nombre de characteres est 1 il faut choisir entre [2-3] sacs d'argents");
				} else if ((nbCh == 2) && (nbSacArgent < (2 * nbCh)) || (nbSacArgent > (3 * nbCh))) {
					errorLabel.setText("Si le nombre de characteres est 2 il faut choisir entre [4-6] sacs d'argents");
				} else if ((nbCh == 3) && (nbSacArgent < (2 * nbCh)) || (nbSacArgent > (3 * nbCh))) {
					errorLabel.setText("Si le nombre de characteres est 3 il faut choisir entre [6-9] sacs d'argents");
				} else if ((nbCh == 4) && (nbSacArgent < (2 * nbCh)) || (nbSacArgent > (3 * nbCh))) {
					errorLabel.setText("Si le nombre de characteres est 4 il faut choisir entre [8-12] sacs d'argents");
				} else {
					errorLabel.setForeground(Color.green);
					errorLabel.setText("Saisie reessite");
					if (!garderJoueurs) {
						Jeu.passer_saisie_joueur1();
					} else {
						Jeu.passer_choix_emplacements(garderJoueurs);
					}
					dispose();
				}
			} catch (NumberFormatException x) {
				errorLabel.setText("Erreur lors de la saisie, reessayez!");
			}
		}

	}

	public int getN() {
		return n;
	}

	public int getM() {
		return m;
	}

	public int get_nbSorties() {
		return nbSorties;
	}

	public int get_nbSacArgent() {
		return nbSacArgent;
	}

	public int get_nbCh() {
		return nbCh;
	}

	public int get_nbObstacles() {
		return nbObstacles;
	}

	public void setNomJ1(String s) {
		nomJ1 = s;
	}

	public void setNomJ2(String s) {
		nomJ2 = s;
	}

	public String getNomJ1() {
		return nomJ1;
	}

	public String getNomJ2() {
		return nomJ2;
	}

}
