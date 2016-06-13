package pl.com.pollub.webmail.auxiliary;

import pl.com.pollub.db.entity.Conference;

import java.time.format.DateTimeFormatter;

/**
 * Created by mmaciasz on 2016-06-13.
 */
public enum ConferenceContentCreator {

    NEW {
        @Override
        public String getContent(Conference conference) {
            return "Konferencja - "
                    + conference.getName()
                    + ". Data rozpoczęcia - "
                    + conference.getDateFrom().format(formatter)
                    + ", data zakończenia - "
                    + conference.getDateTo().format(formatter)
                    + ". Termin zgłaszania mija "
                    + conference.getApplicationDate().format(formatter)
                    + newLine;
        }
    },
    CHANGES_COMMENT {
        @Override
        public String getContent(Conference conference) {
            return "Zmiany w konferencji - "
                    + conference.getName()
                    + ". Data rozpoczęcia - "
                    + conference.getDateFrom().format(formatter)
                    + ", data zakończenia - "
                    + conference.getDateTo().format(formatter)
                    + ". Nastąpiła zmiana w komentarzu."
                    + newLine;
        }
    },
    CHANGES_FILE {
        @Override
        public String getContent(Conference conference) {
            return "Zmiany w konferencji - "
                    + conference.getName()
                    + ". Data rozpoczęcia - "
                    + conference.getDateFrom().format(formatter)
                    + ", data zakończenia - "
                    + conference.getDateTo().format(formatter)
                    + ". Nastąpiła zmiana w plikach."
                    + newLine;
        }
    },
    CHANGES_MAGAZINE {
        @Override
        public String getContent(Conference conference) {
            return "Zmiany w konferencji - "
                    + conference.getName()
                    + ". Data rozpoczęcia - "
                    + conference.getDateFrom().format(formatter)
                    + ", data zakończenia - "
                    + conference.getDateTo().format(formatter)
                    + ". Nastąpiła zmiana w liście czasopism."
                    + newLine;
        }
    },
    CHANGES_APPLICATION {
        @Override
        public String getContent(Conference conference) {
            return "Zmiany w konferencji - "
                    + conference.getName()
                    + ". Data terminu zgłaszania - "
                    + conference.getApplicationDate().format(formatter)
                    + ". Nastąpiła zmiana terminu zgłaszania artykułów."
                    + newLine;
        }
    },
    CHANGES_START {
        @Override
        public String getContent(Conference conference) {
            return "Zmiany w konferencji - "
                    + conference.getName()
                    + ". Data rozpoczęcia - "
                    + conference.getDateFrom().format(formatter)
                    + ", data zakończenia - "
                    + conference.getDateTo().format(formatter)
                    + ". Nastąpiła zmiana terminu konferencji."
                    + newLine;
        }
    },
    CHANGES_FULLTEXT {
        @Override
        public String getContent(Conference conference) {
            return "Zmiany w konferencji - "
                    + conference.getName()
                    + ". Data nadsyłania pełnych tekstów - "
                    + conference.getFullPaperDate().format(formatter)
                    + ". Nastąpiła zmiana terminu nadsyłania pełnych tekstów artykułów."
                    + newLine;
        }
    },
    CHANGES_PAYMENT {
        @Override
        public String getContent(Conference conference) {
            return "Zmiany w konferencji - "
                    + conference.getName()
                    + ". Data wniesienia opłaty - "
                    + conference.getPaymentDate().format(formatter)
                    + ". Nastąpiła terminu opłaty."
                    + newLine;
        }
    },
    CHANGES_OTHERS {
        @Override
        public String getContent(Conference conference) {
            return "Zmiany w konferencji - "
                    + conference.getName()
                    + ". Data rozpoczęcia - "
                    + conference.getDateFrom().format(formatter)
                    + ", data zakończenia - "
                    + conference.getDateTo().format(formatter)
                    + newLine;
        }
    },
    EXPIRE_DEADLINES {
        @Override
        public String getContent(Conference conference) {
            return "Dla konferencji - "
                    + conference.getName()
                    + ". Data terminu zgłaszania - "
                    + conference.getApplicationDate().format(formatter)
                    + ". Mija termin zgłaszania artykułów."
                    + newLine;
        }
    },
    NEARLY_START {
        @Override
        public String getContent(Conference conference) {
            return "Konferencja - "
                    + conference.getName()
                    + ". Data rozpoczęcia - "
                    + conference.getDateFrom().format(formatter)
                    + ", data zakończenia - "
                    + conference.getDateTo().format(formatter)
                    + ". Rozpoczyna się wkrótce."
                    + newLine;
        }
    },
    FULL_TEXT_DEADLINES {
        @Override
        public String getContent(Conference conference) {
            return "Dla konferencji - "
                    + conference.getName()
                    + ". Data nadsyłania pełnych tekstów - "
                    + conference.getFullPaperDate().format(formatter)
                    + ". Mija termin nadsyłania pełnych tekstów artykułów."
                    + newLine;
        }
    },
    PAYMENT_DEADLINES {
        @Override
        public String getContent(Conference conference) {
            return "Dla konferencji - "
                    + conference.getName()
                    + ". Data wniesienia opłaty - "
                    + conference.getPaymentDate().format(formatter)
                    + ". Mija termin wniesienia opłaty."
                    + newLine;
        }
    },
    LAST_YEAR {
        @Override
        public String getContent(Conference conference) {
            return "W zeszłym roku konferencja - "
                    + conference.getName()
                    + ". Data zgłaszania artykułów - "
                    + conference.getApplicationDate().format(formatter)
                    + ". Mijał termin zgłaszania artykułów."
                    + newLine;
        }
    },
    LAST_YEAR_STARTS {
        @Override
        public String getContent(Conference conference) {
            return "W zeszłym roku odbywała się konferencja - "
                    + conference.getName()
                    + ". Data rozpoczęcia - "
                    + conference.getDateFrom().format(formatter)
                    + ", data zakończenia - "
                    + conference.getDateTo().format(formatter)
                    + newLine;
        }
    };

    final String newLine = System.lineSeparator();
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    ConferenceContentCreator() {
    }

    public abstract String getContent(Conference conference);
}
