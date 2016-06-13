package pl.com.pollub.webmail.auxiliary;

import pl.com.pollub.dto.ConferenceWithChanges;

import java.time.format.DateTimeFormatter;

/**
 * Created by mmaciasz on 2016-06-13.
 */
public enum ConferenceContentCreator {

    NEW {
        @Override
        public String getContent(ConferenceWithChanges conference) {
            return "Konferencja - "
                    + conference.getConference().getName()
                    + ". Data rozpoczęcia - "
                    + conference.getConference().getDateFrom().format(formatter)
                    + ", data zakończenia - "
                    + conference.getConference().getDateTo().format(formatter)
                    + ". Termin zgłaszania mija "
                    + conference.getConference().getApplicationDate().format(formatter)
                    + newLine;
        }
    },
    CHANGES_COMMENT {
        @Override
        public String getContent(ConferenceWithChanges conference) {
            return "Zmiany w konferencji - "
                    + conference.getConference().getName()
                    + ". Data rozpoczęcia - "
                    + conference.getConference().getDateFrom().format(formatter)
                    + ", data zakończenia - "
                    + conference.getConference().getDateTo().format(formatter)
                    + ". Nastąpiła zmiana w komentarzu."
                    + newLine;
        }
    },
    CHANGES_FILE {
        @Override
        public String getContent(ConferenceWithChanges conference) {
            return "Zmiany w konferencji - "
                    + conference.getConference().getName()
                    + ". Data rozpoczęcia - "
                    + conference.getConference().getDateFrom().format(formatter)
                    + ", data zakończenia - "
                    + conference.getConference().getDateTo().format(formatter)
                    + ". Nastąpiła zmiana w plikach."
                    + newLine;
        }
    },
    CHANGES_MAGAZINE {
        @Override
        public String getContent(ConferenceWithChanges conference) {
            return "Zmiany w konferencji - "
                    + conference.getConference().getName()
                    + ". Data rozpoczęcia - "
                    + conference.getConference().getDateFrom().format(formatter)
                    + ", data zakończenia - "
                    + conference.getConference().getDateTo().format(formatter)
                    + ". Nastąpiła zmiana w liście czasopism."
                    + newLine;
        }
    },
    CHANGES_FIELDS {
        @Override
        public String getContent(ConferenceWithChanges conference) {
            return "Zmiany w konferencji - "
                    + conference.getConference().getName()
                    + " "
                    + conference.getChanges()
                    + newLine;
        }
    },
    CHANGES_OTHERS {
        @Override
        public String getContent(ConferenceWithChanges conference) {
            return "Inne zmiany w konferencji - "
                    + conference.getConference().getName()
                    + ". Data rozpoczęcia - "
                    + conference.getConference().getDateFrom().format(formatter)
                    + ", data zakończenia - "
                    + conference.getConference().getDateTo().format(formatter)
                    + newLine;
        }
    },
    EXPIRE_DEADLINES {
        @Override
        public String getContent(ConferenceWithChanges conference) {
            return "Dla konferencji - "
                    + conference.getConference().getName()
                    + ". Data terminu zgłaszania - "
                    + conference.getConference().getApplicationDate().format(formatter)
                    + ". Mija termin zgłaszania artykułów."
                    + newLine;
        }
    },
    NEARLY_START {
        @Override
        public String getContent(ConferenceWithChanges conference) {
            return "Konferencja - "
                    + conference.getConference().getName()
                    + ". Data rozpoczęcia - "
                    + conference.getConference().getDateFrom().format(formatter)
                    + ", data zakończenia - "
                    + conference.getConference().getDateTo().format(formatter)
                    + ". Rozpoczyna się wkrótce."
                    + newLine;
        }
    },
    FULL_TEXT_DEADLINES {
        @Override
        public String getContent(ConferenceWithChanges conference) {
            return "Dla konferencji - "
                    + conference.getConference().getName()
                    + ". Data nadsyłania pełnych tekstów - "
                    + conference.getConference().getFullPaperDate().format(formatter)
                    + ". Mija termin nadsyłania pełnych tekstów artykułów."
                    + newLine;
        }
    },
    PAYMENT_DEADLINES {
        @Override
        public String getContent(ConferenceWithChanges conference) {
            return "Dla konferencji - "
                    + conference.getConference().getName()
                    + ". Data wniesienia opłaty - "
                    + conference.getConference().getPaymentDate().format(formatter)
                    + ". Mija termin wniesienia opłaty."
                    + newLine;
        }
    },
    LAST_YEAR {
        @Override
        public String getContent(ConferenceWithChanges conference) {
            return "W zeszłym roku konferencja - "
                    + conference.getConference().getName()
                    + ". Data zgłaszania artykułów - "
                    + conference.getConference().getApplicationDate().format(formatter)
                    + ". Mijał termin zgłaszania artykułów."
                    + newLine;
        }
    },
    LAST_YEAR_STARTS {
        @Override
        public String getContent(ConferenceWithChanges conference) {
            return "W zeszłym roku odbywała się konferencja - "
                    + conference.getConference().getName()
                    + ". Data rozpoczęcia - "
                    + conference.getConference().getDateFrom().format(formatter)
                    + ", data zakończenia - "
                    + conference.getConference().getDateTo().format(formatter)
                    + newLine;
        }
    };

    final String newLine = System.lineSeparator();
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    ConferenceContentCreator() {
    }

    public abstract String getContent(ConferenceWithChanges conference);
}
