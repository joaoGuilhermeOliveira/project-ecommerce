public class Product{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String description;
    @Column(name = "sell_price", nullable = false, length = 10)
    private String sellPrice;
    @Column(name = "cost_price", nullable = false, length = 10)
    private String costPrice;
}