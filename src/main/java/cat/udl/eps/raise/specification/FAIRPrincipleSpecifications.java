package cat.udl.eps.raise.specification;

import cat.udl.eps.raise.domain.FAIRPrinciple;
import cat.udl.eps.raise.domain.Verification;
import cat.udl.eps.raise.domain.RaiseInstance;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class FAIRPrincipleSpecifications {
    public static Specification<FAIRPrinciple> availableFAIRPrinciplesByRaiseInstance(RaiseInstance raiseInstance) {
        return (root, query, criteriaBuilder) -> {
            Join<RaiseInstance, Verification>
                    raiseInstanceFAIRPrincipleVerification = root.join("instance");
            Join<Join<RaiseInstance, Verification>,FAIRPrinciple>
                    joined = root.join("fairPrinciple");
            return criteriaBuilder.equal(joined.get("id"), 1);
        };
    }
}
