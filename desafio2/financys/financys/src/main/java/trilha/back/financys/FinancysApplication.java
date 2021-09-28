package trilha.back.financys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinancysApplication {

	public static void main(String[] args) {

		SpringApplication.run(FinancysApplication.class, args);

		Category category = new Category(4, "Salário", "Recebimento de salário");
		Category category2 = new Category();

		category2.setId(4);
		category2.setName("Salário");
		category2.setDescription("Recebimento de salário");

		System.out.println(category.toString());
		System.out.println(category2.toString());

		Entry entry = new Entry(3, "Salário na empresa X", "Adiantamento quinzenal", "receita",
				"4405.49", "15/09/21", true, 4);

		Entry entry2 = new Entry();

		entry2.setId(3);
		entry2.setName("Salário na empresa X");
		entry2.setDescription("Adiantamento quinzenal");
		entry2.setType("receita");
		entry2.setAmount("4405.49");
		entry2.setDate("15/09/21");
		entry2.isPaid(true);
		entry2.setCategoryId(4);

		System.out.println(entry.toString());
		System.out.println(entry2.toString());

	}

}
