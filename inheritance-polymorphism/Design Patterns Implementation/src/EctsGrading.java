public class EctsGrading implements GradingStrategy {
    @Override
    public String getGrade(double score) {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        if (score >= 50) return "E";
        return "F";
    }
}
