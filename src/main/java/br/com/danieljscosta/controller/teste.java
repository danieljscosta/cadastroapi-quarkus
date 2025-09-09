<routes xmlns="http://camel.apache.org/schema/spring">
    <route id="rota-xml">
        <from uri="platform-http:/say/hello"/>
        <setBody>
            <constant>Olá do mundo XML DSL!</constant>
        </setBody>
        <to uri="log:info"/>
    </route>
</routes>