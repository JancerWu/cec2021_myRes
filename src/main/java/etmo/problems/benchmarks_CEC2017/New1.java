package etmo.problems.benchmarks_CEC2017;

import etmo.core.Problem;
import etmo.core.ProblemSet;
import etmo.problems.base.CEC2017.IO;
import etmo.problems.base.CEC2017.mmdtlz;
import etmo.problems.base.CEC2017.mmzdt;

import java.io.IOException;

public class New1 {
    public static ProblemSet getProblem() throws IOException {
        ProblemSet ps1 = getT1();
        ProblemSet ps2 = getT2();
        ProblemSet problemSet = new ProblemSet(2);

        problemSet.add(ps1.get(0));
        problemSet.add(ps2.get(0));
        return problemSet;

    }


    public static ProblemSet getT1() throws IOException {
        ProblemSet problemSet = new ProblemSet(1);

        mmzdt prob = new mmzdt(50, 1,  -1,1);
        prob.setGType("ackley");
        prob.setHType("convex");

        ((Problem)prob).setName("CILS2");

        problemSet.add(prob);
        return problemSet;
    }


    public static ProblemSet getT2() throws IOException {
        ProblemSet problemSet = new ProblemSet(1);


        mmzdt prob = new mmzdt(50, 1,  -1,1);
        prob.setGType("rastrigin");
        prob.setHType("concave");


        double[][] matrix = IO.readMatrixFromFile("MData/CEC2017/M_PIMS_2.txt");
        prob.setRotationMatrix(matrix);

        ((Problem)prob).setName("PIMS2");

        problemSet.add(prob);

        return problemSet;
    }

}
