package com.github.cocolollipop.mido_svg.view;

import java.io.File;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import com.github.cocolollipop.mido_svg.model.DataBase;
import com.github.cocolollipop.mido_svg.paper.Paper;
import com.github.cocolollipop.mido_svg.svg_generator.DrawerSVGGen;
import com.github.cocolollipop.mido_svg.svg_generator.Settings;
import com.github.cocolollipop.mido_svg.university.components.Formation;

public class GUISVGGeneratorbis {

	protected Shell shell;
	private Button btnCheckButtonAutre;
	private Button btnCheckButtonA4;
	private Button btnCheckButtonA3;
	private Text text;
	private Text text_1;
	private Label lblLargeur;
	private Label lblLongueur;
	private Label lblMsg1;
	private Label lblChoixDeLaffichage;
	private Button btnLesprerequis;
	private Button btnLesEnseignants;
	private Button btnLesMatires;
	private Button btnLesResponsables;
	private Button btnLicence;
	private Button btnMaster;
	private Label lblEtou;
	private Label lblOptionsDaffichage;
	private Button btnLeModeDadmission;
	private Button btnFermer;
	private Button btnPush;
	private Label labelEtat;

	private boolean affFormationLicence;
	private boolean affFormationMaster;
	private boolean affResponsable;
	private boolean affAdmission;
	private boolean affSubject;
	private boolean affTeacher;
	private boolean affPrereq;
	private String form;
	private DataBase datas;
	private Settings settings;
	private int width;
	private int height;

	private DrawerSVGGen svg = new DrawerSVGGen();
	private Paper format = new Paper();
	private Formation formation; // A VOIR
	private Spinner spinnerWidth;
	private Spinner spinnerHeight;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GUISVGGeneratorbis window = new GUISVGGeneratorbis();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		createEvents();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(550, 592);
		shell.setText("SWT Application");
		shell.setLayout(null);

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(226, 47, 108, 27);
		lblNewLabel.setText("MIDO Application");

		Label lblChoixDuFormat = new Label(shell, SWT.NONE);
		lblChoixDuFormat.setBounds(32, 130, 102, 14);
		lblChoixDuFormat.setText("Choix du format: ");

		btnCheckButtonA3 = new Button(shell, SWT.RADIO);

		btnCheckButtonA3.setBounds(52, 160, 94, 18);
		btnCheckButtonA3.setText("A3");

		btnCheckButtonA4 = new Button(shell, SWT.RADIO);
		btnCheckButtonA4.setSelection(true);

		btnCheckButtonA4.setBounds(170, 160, 94, 18);
		btnCheckButtonA4.setText("A4");

		btnCheckButtonAutre = new Button(shell, SWT.RADIO);

		btnCheckButtonAutre.setBounds(287, 160, 94, 18);
		btnCheckButtonAutre.setText("Autre");

		lblLargeur = new Label(shell, SWT.NONE);
		lblLargeur.setBounds(404, 146, 59, 18);
		lblLargeur.setText("");

		lblLongueur = new Label(shell, SWT.NONE);
		lblLongueur.setBounds(404, 186, 59, 18);
		lblLongueur.setText("");

		lblMsg1 = new Label(shell, SWT.NONE);
		lblMsg1.setBounds(374, 108, 166, 14);
		lblMsg1.setText("");

		lblChoixDeLaffichage = new Label(shell, SWT.NONE);
		lblChoixDeLaffichage.setBounds(32, 222, 128, 14);
		lblChoixDeLaffichage.setText("Choix de Parcours :");

		btnLesprerequis = new Button(shell, SWT.CHECK);

		btnLesprerequis.setBounds(131, 393, 108, 18);
		btnLesprerequis.setText("Les Prérequis");

		btnLesEnseignants = new Button(shell, SWT.CHECK);

		btnLesEnseignants.setBounds(380, 348, 108, 18);
		btnLesEnseignants.setText("Les enseignants");

		btnLesMatires = new Button(shell, SWT.CHECK);

		btnLesMatires.setBounds(226, 348, 94, 18);
		btnLesMatires.setText("Les matières");

		btnLesResponsables = new Button(shell, SWT.CHECK);

		btnLesResponsables.setBounds(52, 348, 117, 18);
		btnLesResponsables.setText("Les responsables ");

		btnLicence = new Button(shell, SWT.CHECK);

		btnLicence.setBounds(131, 257, 90, 18);
		btnLicence.setText("Licence ");

		btnMaster = new Button(shell, SWT.CHECK);

		btnMaster.setBounds(342, 257, 90, 18);
		btnMaster.setText("Master");

		lblEtou = new Label(shell, SWT.NONE);
		lblEtou.setBounds(260, 260, 59, 14);
		lblEtou.setText("Et/Ou");

		lblOptionsDaffichage = new Label(shell, SWT.NONE);
		lblOptionsDaffichage.setBounds(32, 305, 114, 14);
		lblOptionsDaffichage.setText("Options d'affichage :");

		btnLeModeDadmission = new Button(shell, SWT.CHECK);

		btnLeModeDadmission.setBounds(314, 393, 133, 18);
		btnLeModeDadmission.setText("Le mode d'admission");

		btnPush = new Button(shell, SWT.NONE);

		btnPush.setBounds(404, 516, 119, 28);
		btnPush.setText("Générer le SVG");

		btnFermer = new Button(shell, SWT.NONE);

		btnFermer.setBounds(32, 516, 94, 28);
		btnFermer.setText("Fermer");

		labelEtat = new Label(shell, SWT.NONE);
		labelEtat.setBounds(97, 467, 366, 14);

	}

	private void createEvents() {

		btnCheckButtonAutre.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnCheckButtonAutre.getSelection()) {

					lblMsg1.setText("Saisissez le format souhaité :");
					lblLargeur.setText("Largeur");
					lblLongueur.setText("Longueur");

					spinnerWidth = new Spinner(shell, SWT.BORDER);
					spinnerWidth.setBounds(472, 141, 52, 22);

					spinnerHeight = new Spinner(shell, SWT.BORDER);
					spinnerHeight.setBounds(472, 182, 52, 22);

					height = spinnerHeight.getSelection();
					width = spinnerWidth.getSelection();

				}

			}
		});

		btnCheckButtonA4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (btnCheckButtonA3.getSelection()) {
					form = "A4";
				}

			}
		});

		btnCheckButtonA3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (btnCheckButtonA3.getSelection()) {
					form = "A3";
				}

			}
		});

		/**
		 * Check box to choose Licence
		 * 
		 */

		btnLicence.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnLicence.getSelection()) {
					affFormationLicence = false;
				} else {
					affFormationLicence = true;
				}

			}
		});

		/**
		 * Check box to choose Master
		 * 
		 */

		btnMaster.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (btnMaster.getSelection()) {
					affFormationMaster = false;
				} else {
					affFormationMaster = true;
				}
			}
		});

		/**
		 * Check box to choose display responsibles of a Formation
		 * 
		 */

		btnLesResponsables.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (btnLesResponsables.getSelection()) {
					affResponsable = false;
				} else {
					affResponsable = true;

				}
			}
		});

		/**
		 * Check box to choose display Subjects of a Formation
		 * 
		 */

		btnLesMatires.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnLesMatires.getSelection()) {
					affSubject = false;
				} else {
					affSubject = true;

				}

			}
		});

		btnLesEnseignants.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnLesEnseignants.getSelection()) {
					affTeacher = false;
				} else {
					affTeacher = true;

				}
			}
		});

		/**
		 * Check box to choose display Prerequisites of a subject
		 * 
		 */

		btnLesprerequis.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (btnLesprerequis.getSelection()) {
					affPrereq = false;
				} else {
					affPrereq = true;

				}
			}
		});

		/**
		 * Check box to choose display "Admission" or not
		 * 
		 */

		btnLeModeDadmission.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (btnLeModeDadmission.getSelection()) {
					affAdmission = false;
				} else {
					affAdmission = true;

				}
			}
		});

		/**** Close Button ****/

		btnFermer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});

		/**** Push Button to generate the SVG ****/
		btnPush.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				try {
					if (btnCheckButtonA3.getSelection() || btnCheckButtonA4.getSelection()) {
						settings = new Settings(affFormationLicence, affFormationMaster, affResponsable, affAdmission,
								affSubject, affTeacher, affPrereq, form);
					} else if (btnCheckButtonAutre.getSelection()) {
						settings = new Settings(affFormationLicence, affFormationMaster, affResponsable, affAdmission,
								affSubject, affTeacher, affPrereq, width, height);
					}

					svg.paint(settings, datas);

					File file = new File("./svg/outLicence.svg");

					try {
						java.awt.Desktop.getDesktop().open(file);
					} catch (IOException exc) {
						System.out.println("Exception: " + exc.toString());
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}

				labelEtat.setText(" SVG généré ");
			}
		});

	}
}