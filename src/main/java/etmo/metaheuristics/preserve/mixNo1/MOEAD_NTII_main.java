package etmo.metaheuristics.preserve.mixNo1;

import etmo.core.*;
import etmo.metaheuristics.utils.printIGD;
import etmo.operators.crossover.CrossoverFactory;
import etmo.operators.mutation.MutationFactory;
import etmo.operators.selection.SelectionFactory;
import etmo.problems.benchmarks_CEC2017.*;
import etmo.problems.benchmarks_ETMO.*;
import etmo.qualityIndicator.QualityIndicator;
import etmo.util.JMException;
import etmo.util.comparators.LocationComparator;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;

public class MOEAD_NTII_main {
    public static void main(String[] args) throws IOException, JMException, ClassNotFoundException {
        ProblemSet problemSet;

        Algorithm algorithm; // The algorithm to use
        Operator crossover; // Crossover operator
        Operator mutation; // Mutation operator
        Operator selection;

        HashMap parameters; // Operator parameters

        double[] CR = new double[15];
        for (int i = 1; i <= 10; i++){
            CR[i] = (double)i / 10.0;
        }

        for (int crI = 1; crI <= 10; crI++){
            System.out.println("rmp = " + CR[crI]);
            for (int pCase = 1; pCase <= 9; pCase++ ){
                switch (pCase){
//                case 1:
//                    problemSet = CPLX1.getProblem();
//                    break;
//                case 2:
//                    problemSet = CPLX2.getProblem();
//                    break;
//                case 3:
//                    problemSet = CPLX3.getProblem();
//                    break;
//                case 4:
//                    problemSet = CPLX4.getProblem();
//                    break;
//                case 5:
//                    problemSet = CPLX5.getProblem();
//                    break;
//                case 6:
//                    problemSet = CPLX6.getProblem();
//                    break;
//                case 7:
//                    problemSet = CPLX7.getProblem();
//                    break;
//                case 8:
//                    problemSet = CPLX8.getProblem();
//                    break;
//                case 9:
//                    problemSet = CPLX9.getProblem();
//                    break;
//                case 10:
//                    problemSet = CPLX10.getProblem();
//                    break;

                    case 1:
                        problemSet = CIHS.getProblem();
                        break;
                    case 2:
                        problemSet = CIMS.getProblem();
                        break;
                    case 3:
                        problemSet = CILS.getProblem();
                        break;
                    case 4:
                        problemSet = PIHS.getProblem();
                        break;
                    case 5:
                        problemSet = PIMS.getProblem();
                        break;
                    case 6:
                        problemSet = PILS.getProblem();
                        break;
                    case 7:
                        problemSet = NIHS.getProblem();
                        break;
                    case 8:
                        problemSet = NIMS.getProblem();
                        break;
                    case 9:
                        problemSet = NILS.getProblem();
                        break;

//                case 1:
//                    problemSet = ETMOF1.getProblem();
//                    break;
//                case 2:
//                    problemSet = ETMOF2.getProblem();
//                    break;
//                case 3:
//                    problemSet = ETMOF4.getProblem();
//                    break;
//                case 4:
//                    problemSet = ETMOF5.getProblem();
//                    break;
//                case 5:
//                    problemSet = ETMOF6.getProblem();
//                    break;
//                case 6:
//                    problemSet = ETMOF7.getProblem();
//                    break;
//                case 7:
//                    problemSet = ETMOF8.getProblem();
//                    break;
//                case 8:
//                    problemSet = ETMOF8.getProblem();
//                    break;
//                case 9:
//                    problemSet = ETMOF9.getProblem();
//                    break;
//                case 10:
//                    problemSet = ETMOF10.getProblem();
//                    break;
                    case 11:
                        problemSet = ETMOF11.getProblem();
                        break;
                    case 12:
                        problemSet = ETMOF12.getProblem();
                        break;
                    case 13:
                        problemSet = ETMOF13.getProblem();
                        break;
                    case 14:
                        problemSet = ETMOF14.getProblem();
                        break;
                    case 15:
                        problemSet = ETMOF15.getProblem();
                        break;
                    case 16:
                        problemSet = ETMOF16.getProblem();
                        break;
                    case 17:
                        problemSet = ETMOF17.getProblem();
                        break;
                    case 18:
                        problemSet = ETMOF18.getProblem();
                        break;
                    case 19:
                        problemSet = ETMOF19.getProblem();
                        break;
                    case 20:
                        problemSet = ETMOF20.getProblem();
                        break;
                    case 21:
                        problemSet = ETMOF21.getProblem();
                        break;
                    case 22:
                        problemSet = ETMOF22.getProblem();
                        break;
                    case 23:
                        problemSet = ETMOF23.getProblem();
                        break;
                    case 24:
                        problemSet = ETMOF24.getProblem();
                        break;
                    default:
                        problemSet = ETMOF1.getProblem();
                }

                int taskNumber = problemSet.size();

                String[] pf = new String[taskNumber];
//            for (int i = 0; i < pf.length; i++){
//                pf[i] = "PF/StaticPF/" + problemSet.get(i).getHType() + "_" + problemSet.get(i).getNumberOfObjectives() + "D.pf";
//            }
                for (int i = 0; i < pf.length; i++){
                    pf[i] = "PF/cec2017/" + problemSet.get(i).getHType() + ".pf";
                }

                algorithm = new MOEAD_NTII(problemSet);

                algorithm.setInputParameter("populationSize", 100);
                algorithm.setInputParameter("maxEvaluations", 100 * taskNumber * 1000);

                algorithm.setInputParameter("dataDirectory", "resources/weightVectorFiles/moead");


                algorithm.setInputParameter("T", 10);
                algorithm.setInputParameter("delta", 0.3);
                algorithm.setInputParameter("nr", 2);
                algorithm.setInputParameter("rmp", CR[crI]);


                parameters = new HashMap();
                parameters.put("CR", 0.8);
                parameters.put("F", 0.5);
                crossover = CrossoverFactory.getCrossoverOperator("DifferentialEvolutionCrossover",parameters);

                // Mutation operator
                parameters = new HashMap();
                parameters.put("probability", 1.0 / problemSet.get(0).getNumberOfVariables());
                parameters.put("distributionIndex", 20.0);
                mutation = MutationFactory.getMutationOperator("PolynomialMutation", parameters);

                parameters = new HashMap() ;
                parameters.put("comparator", new LocationComparator()) ;
                selection = SelectionFactory.getSelectionOperator("BinaryTournament",
                        parameters);


                algorithm.addOperator("crossover", crossover);
                algorithm.addOperator("mutation", mutation);
                algorithm.addOperator("selection", selection);



//                System.out.println("RunID\t" + "IGD for " + problemSet2.get(0).getName());
                DecimalFormat form = new DecimalFormat("#.####E0");


                int times = 10;
                double ave[] = new double[taskNumber];
                double cpIGD[][] = new double[taskNumber][times];

                for (int t = 1; t <= times; t++){
                    SolutionSet[] resPopulation = ((MOEAD_NTII) algorithm).execute2();


                    SolutionSet[] res = new SolutionSet[taskNumber];
                    for (int i = 0; i < taskNumber; i++){
                        res[i] = new SolutionSet();
                        for (int k = 0; k < resPopulation[i].size(); k++){
                            Solution sol = resPopulation[i].get(k);
                            int start = problemSet.get(i).getStartObjPos();
                            int end = problemSet.get(i).getEndObjPos();
                            Solution newSolution = new Solution(end - start + 1);

                            for (int l = start; l <= end; l++)
                                newSolution.setObjective(l - start, sol.getObjective(l));
                            res[i].add(newSolution);
                        }
                    }

                    double igd;
//				System.out.print(t + "\t");
                    for(int i = 0; i < taskNumber; i++){
                        QualityIndicator indicator = new QualityIndicator(problemSet.get(i), pf[i]);
                        if(res[i].size()==0)
                            continue;
                        igd =  indicator.getIGD(res[i]);
//					System.out.print(form.format(igd) + "\t" );
                        ave[i] += igd;
                        cpIGD[i][t - 1] = igd;
                    }

                }

                for(int i=0;i<taskNumber;i++)
//				System.out.println("Average IGD for " + problemSet.get(i).getName()+ ": " + form.format(ave[i] / times));
                    System.out.println(form.format(ave[i] / times));



//
                String path = "new rmp=" + CR[crI] + ".txt";
                printIGD.printIGDtoText(path, cpIGD, taskNumber, times);


//            String path = "";
//            try {
//                FileOutputStream fos = new FileOutputStream(path);
//                OutputStreamWriter osw = new OutputStreamWriter(fos);
//                BufferedWriter bw = new BufferedWriter(osw);
//
//                if (ave > 0) {
//                    int numberOfVariables = solutionsList_.get(0).getDecisionVariables().length;
//                    for (Solution aSolutionsList_ : solutionsList_) {
//                        for (int j = 0; j < numberOfVariables; j++)
//                            bw.write(aSolutionsList_.getDecisionVariables()[j].toString() + " ");
//                        bw.newLine();
//                    }
//                }
//                bw.close();
//            } catch (IOException e) {
//                Configuration.logger_.severe("Error acceding to the file");
//                e.printStackTrace();
//            }

//            QualityIndicator indicator = new QualityIndicator(problemSet.get(0), pf[0]);
//            int times = 21;
//            double aveIGD = 0;
//            for (int i = 1; i <= times; i++) {
//                SolutionSet population = algorithm.execute();
////                    chart.saveChart("moead", BitmapEncoder.BitmapFormat.PNG);
////                    population.printObjectivesToFile("MOEAD_"+problemSet2.get(0).getNumberOfObjectives()+"Obj_"+
////                        problemSet2.get(0).getName()+ "_" + problemSet2.get(0).getNumberOfVariables() + "D_run"+i+".txt");
//                double igd = indicator.getIGD(population);
//                aveIGD += igd;
////                    System.out.println(i + "\t" + form.format(igd));
////                    System.out.println(i + "\t" + form.format(igd));
////                    resources/weightVectorFiles/moead/W3D_100.dat
//
//
//            }
////                System.out.println("Average IGD for " + problemSet2.get(0).getName() + ": " + form.format(aveIGD / times));
//            System.out.println(form.format(aveIGD / times));
//            //                System.out.println();


            }

        }


    }
}
