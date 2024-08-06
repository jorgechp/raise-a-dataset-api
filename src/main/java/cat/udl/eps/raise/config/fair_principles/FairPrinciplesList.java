package cat.udl.eps.raise.config.fair_principles;

import cat.udl.eps.raise.domain.FAIRCategories;
import cat.udl.eps.raise.domain.FAIRPrinciple;

import java.util.ArrayList;
import java.util.List;

public class FairPrinciplesList {
    private List<FAIRPrinciple> fairPrincipleList =  new ArrayList<>();

    public List<FAIRPrinciple> getFairPrincipleList(){
        // F1
        FAIRPrinciple f1 = new FAIRPrinciple();
        f1.setDifficulty((short) 3);
        f1.setCategory(FAIRCategories.FINDABILITY);
        f1.setNamePrefix("F1");
        f1.setName("(Meta)data are assigned a globally unique and persistent identifier");
        f1.setUrl("https://www.go-fair.org/fair-principles/f1-meta-data-assigned-globally-unique-persistent-identifiers/");
        f1.setDescription("Globally unique and persistent identifiers remove ambiguity in the meaning of your published data by assigning a unique identifier to every element of metadata and every concept/measurement in your dataset");
        fairPrincipleList.add(f1);

        // F2
        FAIRPrinciple f2 = new FAIRPrinciple();
        f2.setDifficulty((short) 4);
        f2.setCategory(FAIRCategories.FINDABILITY);
        f2.setNamePrefix("F2");
        f2.setName("Data are described with rich metadata");
        f2.setUrl("https://www.go-fair.org/fair-principles/f2-data-are-described-with-rich-metadata/");
        f2.setDescription("Rich metadata provides detailed information about the data, making it easier to find and understand.");
        fairPrincipleList.add(f2);

        // F3
        FAIRPrinciple f3 = new FAIRPrinciple();
        f3.setDifficulty((short) 2);
        f3.setCategory(FAIRCategories.FINDABILITY);
        f3.setNamePrefix("F3");
        f3.setName("Metadata clearly and explicitly include the identifier of the data they describe");
        f3.setUrl("https://www.go-fair.org/fair-principles/f3-metadata-clearly-explicitly-include-identifier-data-describe/");
        f3.setDescription("Including identifiers in metadata ensures that the data can be accurately referenced and retrieved.");
        fairPrincipleList.add(f3);

        // F4
        FAIRPrinciple f4 = new FAIRPrinciple();
        f4.setDifficulty((short) 5);
        f4.setCategory(FAIRCategories.FINDABILITY);
        f4.setNamePrefix("F4");
        f4.setName("(Meta)data are registered or indexed in a searchable resource");
        f4.setUrl("https://www.go-fair.org/fair-principles/f4-meta-data-registered-indexed-searchable-resource/");
        f4.setDescription("Registering or indexing data in searchable resources makes it easier for others to find and use the data.");
        fairPrincipleList.add(f4);

        // A1
        FAIRPrinciple a1 = new FAIRPrinciple();
        a1.setDifficulty((short) 3);
        a1.setCategory(FAIRCategories.ACCESSIBILITY);
        a1.setNamePrefix("A1");
        a1.setName("(Meta)data are retrievable by their identifier using a standardised communications protocol");
        a1.setUrl("https://www.go-fair.org/fair-principles/a1-meta-data-retrievable-identifier-using-standardised-communications-protocol/");
        a1.setDescription("Standardised protocols ensure that data can be reliably retrieved using their identifiers.");
        fairPrincipleList.add(a1);

        // A1.1
        FAIRPrinciple a11 = new FAIRPrinciple();
        a11.setDifficulty((short) 2);
        a11.setCategory(FAIRCategories.ACCESSIBILITY);
        a11.setNamePrefix("A1.1");
        a11.setName("The protocol is open, free, and universally implementable");
        a11.setUrl("https://www.go-fair.org/fair-principles/a1-1-protocol-open-free-universally-implementable/");
        a11.setDescription("Open and free protocols ensure that data can be accessed by anyone, anywhere.");
        fairPrincipleList.add(a11);

        // A2
        FAIRPrinciple a2 = new FAIRPrinciple();
        a2.setDifficulty((short) 3);
        a2.setCategory(FAIRCategories.ACCESSIBILITY);
        a2.setNamePrefix("A2");
        a2.setName("Metadata are accessible, even when the data are no longer available");
        a2.setUrl("https://www.go-fair.org/fair-principles/a2-metadata-accessible-even-when-data-no-longer-available/");
        a2.setDescription("Ensuring that metadata remains accessible even if the data is no longer available helps maintain the context and understanding of the data.");
        fairPrincipleList.add(a2);

        // I1
        FAIRPrinciple i1 = new FAIRPrinciple();
        i1.setDifficulty((short) 5);
        i1.setCategory(FAIRCategories.INTEROPERABILITY);
        i1.setNamePrefix("I1");
        i1.setName("(Meta)data use a formal, accessible, shared, and broadly applicable language for knowledge representation");
        i1.setUrl("https://www.go-fair.org/fair-principles/i1-meta-data-use-formal-accessible-shared-broadly-applicable-language-knowledge-representation/");
        i1.setDescription("Using formal and shared languages for knowledge representation ensures that data can be understood and used across different systems.");
        fairPrincipleList.add(i1);

        // I2
        FAIRPrinciple i2 = new FAIRPrinciple();
        i2.setDifficulty((short) 3);
        i2.setCategory(FAIRCategories.INTEROPERABILITY);
        i2.setNamePrefix("I2");
        i2.setName("(Meta)data use vocabularies that follow FAIR principles");
        i2.setUrl("https://www.go-fair.org/fair-principles/i2-meta-data-use-vocabularies-follow-fair-principles/");
        i2.setDescription("Using FAIR-compliant vocabularies ensures that data can be easily integrated and used with other datasets.");
        fairPrincipleList.add(i2);

        // I3
        FAIRPrinciple i3 = new FAIRPrinciple();
        i3.setDifficulty((short) 3);
        i3.setCategory(FAIRCategories.INTEROPERABILITY);
        i3.setNamePrefix("I3");
        i3.setName("(Meta)data include qualified references to other (meta)data");
        i3.setUrl("https://www.go-fair.org/fair-principles/i3-meta-data-include-qualified-references-other-meta-data/");
        i3.setDescription("Including references to other metadata ensures that data can be linked and integrated with other datasets.");
        fairPrincipleList.add(i3);

        // R1
        FAIRPrinciple r1 = new FAIRPrinciple();
        r1.setDifficulty((short) 4);
        r1.setCategory(FAIRCategories.REUSABILITY);
        r1.setNamePrefix("R1");
        r1.setName("(Meta)data are richly described with a plurality of accurate and relevant attributes");
        r1.setUrl("https://www.go-fair.org/fair-principles/r1-meta-data-richly-described-plurality-accurate-relevant-attributes/");
        r1.setDescription("Rich descriptions with accurate and relevant attributes ensure that data can be reused effectively.");
        fairPrincipleList.add(r1);

        // R1.1
        FAIRPrinciple r11 = new FAIRPrinciple();
        r11.setDifficulty((short) 2);
        r11.setCategory(FAIRCategories.REUSABILITY);
        r11.setNamePrefix("R1.1");
        r11.setName("(Meta)data are released with a clear and accessible data usage license");
        r11.setUrl("https://www.go-fair.org/fair-principles/r1-1-meta-data-released-clear-accessible-data-usage-license/");
        r11.setDescription("Clear and accessible data usage licenses ensure that data can be reused legally and ethically.");
        fairPrincipleList.add(r11);

        // R1.2
        FAIRPrinciple r12 = new FAIRPrinciple();
        r12.setDifficulty((short) 3);
        r12.setCategory(FAIRCategories.REUSABILITY);
        r12.setNamePrefix("R1.2");
        r12.setName("(Meta)data are associated with detailed provenance");
        r12.setUrl("https://www.go-fair.org/fair-principles/r1-2-meta-data-associated-detailed-provenance/");
        r12.setDescription("Detailed provenance information ensures that the origins and history of the data are well-documented, supporting its reuse.");
        fairPrincipleList.add(r12);

        // R1.3
        FAIRPrinciple r13 = new FAIRPrinciple();
        r13.setDifficulty((short) 3);
        r13.setCategory(FAIRCategories.REUSABILITY);
        r13.setNamePrefix("R1.3");
        r13.setName("(Meta)data meet domain-relevant community standards");
        r13.setUrl("https://www.go-fair.org/fair-principles/r1-3-meta-data-meet-domain-relevant-community-standards/");
        r13.setDescription("Meeting community standards ensures that data is reusable within its specific domain.");
        fairPrincipleList.add(r13);

        return fairPrincipleList;
    }

}
