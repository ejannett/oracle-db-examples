package com.oracle.jdbc.samples.interceptor;

import oracle.jdbc.TraceEventListener;
import oracle.jdbc.spi.OracleResourceProvider;
import oracle.jdbc.spi.TraceEventListenerProvider;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SQLStatementInterceptorProvider implements TraceEventListenerProvider {
  private static final InterceptorParameter parameter = new InterceptorParameter();
  private static final InterceptorJSONParameter jParameter = new InterceptorJSONParameter();
  private static final List<Parameter> parameters = List.of(parameter,
                                                            jParameter);

  @Override
  public TraceEventListener getTraceEventListener(Map<Parameter, CharSequence> parameterMap) {
    // we can load from file or JSON content.
    String rulesAsString = parameterMap.get(jParameter).toString();

    RuleConfiguration configuration;
    try {
      configuration = RuleConfiguration.fromJSON(rulesAsString);
    } catch (Exception e) {
      // what shall I do now ?
      return null;
    }
    return new SQLStatementInterceptor(configuration.getRules());
  }

  @Override
  public String getName() {
    return SQLStatementInterceptorProvider.class.getName();
  }

  @Override
  public Collection<? extends Parameter> getParameters() {
    return parameters;
  }
}
