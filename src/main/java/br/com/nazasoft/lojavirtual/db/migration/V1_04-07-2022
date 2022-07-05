select constraint_name  from information_schema.constraint_column_usage
where  table_name = 'usuarios_acessos' and column_name= 'acesso_id'
and constraint_name <> 'unique_acesso_user';

alter table usuarios_acesso drop constraint  "constraint";