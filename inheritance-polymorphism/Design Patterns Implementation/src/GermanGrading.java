public class GermanGrading implements GradingStrategy {
    @Override
    public String getGrade(double score) {
        if (score >= 90) return "1.0";
        if (score >= 80) return "2.0";
        if (score >= 70) return "3.0";
        if (score >= 60) return "4.0";
        return "5.0";
    }
}
