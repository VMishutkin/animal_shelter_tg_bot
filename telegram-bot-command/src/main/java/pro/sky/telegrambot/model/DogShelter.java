package pro.sky.telegrambot.model;

import org.springframework.stereotype.Component;
import pro.sky.telegrambot.constants.FileNames;

@Component
public class DogShelter extends Shelter {

    private String cynologystsAdvices;
    private String approvedCynologysts;
    private String approvedCynologystsFileName = FileNames.APPROVED_CYNOLOGYSTS_DOG_SHELTER;
    private String cynologystsAdvicesFileName = FileNames.CYNOLOGYSTS_ADVICES_DOG_SHELTER;

    public DogShelter() {
        super(FileNames.GREETINGS_DOG_SHELTER, FileNames.DESCRIPTION_DOG_SHELTER,
                FileNames.SCHEDULE_AND_ADDRESS_DOG_SHELTER,
                FileNames.DOCUMENTS_FOR_ADOPTION_DOG_SHELTER, FileNames.SAFETY_PRECUATIONS_DOG_SHELTER,
                FileNames.DECLINE_REASONS_DOG_SHELTER, FileNames.MEETING_RULES_DOG_SHELTER,
                FileNames.TRANSPORTATION_RECOMMENDATIONS_DOG_SHELTER, FileNames.HOME_IMPROVEMENTS_FOR_PUPPIES_DOG_SHELTER,
                FileNames.HOME_IMPROVEMENTS_FOR_DISABLED_DOG_SHELTER, FileNames.HOME_IMPROVEMENTS_FOR_ADULTS_DOG_SHELTER);
        this.cynologystsAdvices = readStringsFromFile(cynologystsAdvicesFileName);
        this.approvedCynologysts = readStringsFromFile(approvedCynologystsFileName);
    }

    @Override
    protected void initializeFileNames(String greetingsFileName,
                                       String descriptionFileName,
                                       String scheduleAndAddressFileName,
                                       String documentsForAdoptionFileName,
                                       String safetyPrecuationsFileName,
                                       String declineReasonsFileName,
                                       String meetingRulesFileName,
                                       String transportationRecommendationsFileName,
                                       String homeImprovementsForPuppiesFileName,
                                       String homeImprovementsForDisabledFileName,
                                       String homeImprovementsForAdultsFileName) {
        super.initializeFileNames(greetingsFileName,
                descriptionFileName,
                scheduleAndAddressFileName,
                documentsForAdoptionFileName,
                safetyPrecuationsFileName,
                declineReasonsFileName,
                meetingRulesFileName,
                transportationRecommendationsFileName,
                homeImprovementsForPuppiesFileName,
                homeImprovementsForDisabledFileName,
                homeImprovementsForAdultsFileName);
        this.cynologystsAdvicesFileName = FileNames.APPROVED_CYNOLOGYSTS_DOG_SHELTER;
        this.approvedCynologystsFileName = FileNames.CYNOLOGYSTS_ADVICES_DOG_SHELTER;
    }

    @Override
    public void updateInfoAboutShelter() {
        super.updateInfoAboutShelter();
        cynologystsAdvices = readStringsFromFile(cynologystsAdvicesFileName);
        approvedCynologysts = readStringsFromFile(approvedCynologystsFileName);
    }


    public String getCynologystsAdvices() {
        return cynologystsAdvices;
    }

    public String getApprovedCunologysts() {
        return approvedCynologysts;
    }
}
