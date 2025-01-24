-- public.cliente definition

-- Drop table

-- DROP TABLE public.cliente;

CREATE TABLE public.cliente (
                                cpf varchar(255) NOT NULL,
                                nome varchar(255) NULL,
                                email varchar(255) NULL,
                                CONSTRAINT cliente_pk PRIMARY KEY (cpf)
);


-- public.produto definition

-- Drop table

-- DROP TABLE public.produto;

CREATE TABLE public.produto (
                                id int8 NOT NULL,
                                nome varchar NULL,
                                categoria varchar NULL,
                                preco numeric(7, 2) NULL,
                                CONSTRAINT produto_pk PRIMARY KEY (id)
);

-- public.pedido definition

-- Drop table

-- DROP TABLE public.pedido;

CREATE TABLE public.pedido (
                               id int8 NOT NULL,
                               cliente_cpf varchar NULL,
                               status varchar NULL,
                               CONSTRAINT pedido_pk PRIMARY KEY (id)
);


-- public.pedido_produtos definition

-- Drop table

-- DROP TABLE public.pedido_produtos;

CREATE TABLE public.pedido_produtos (
                                        pedido_id varchar NULL,
                                        produto_id varchar NULL
);