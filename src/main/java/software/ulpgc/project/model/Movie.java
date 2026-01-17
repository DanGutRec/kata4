package software.ulpgc.project.model;

public record Movie(String tconst,
                    String titleType,
                    String primaryTitle,
                    String  originalTitle,
                    boolean isAdult ,
                    int startYear,
                    int endYear,
                    int runtimeMinutes,
                    String[] genres) {
}


