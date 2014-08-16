/*
 * Copyright (c) 2014 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.oomph.targlets.internal.core;

import org.eclipse.oomph.resources.ResourcesUtil;
import org.eclipse.oomph.targlets.core.TargletContainerEvent;
import org.eclipse.oomph.targlets.core.TargletContainerEvent.WorkspaceUpdateFinishedEvent;
import org.eclipse.oomph.util.Pair;
import org.eclipse.oomph.util.pde.TargetPlatformListener;
import org.eclipse.oomph.util.pde.TargetPlatformRunnable;
import org.eclipse.oomph.util.pde.TargetPlatformUtil;

import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.pde.core.target.ITargetDefinition;
import org.eclipse.pde.core.target.ITargetLocation;
import org.eclipse.pde.core.target.ITargetPlatformService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Eike Stepper
 */
public final class WorkspaceIUImporter
{
  public static final WorkspaceIUImporter INSTANCE = new WorkspaceIUImporter();

  private final TargetPlatformListener listener = new TargetPlatformListener()
  {
    public void targetDefinitionActivated(ITargetDefinition oldTargetDefinition, ITargetDefinition newTargetDefinition) throws Exception
    {
      if (newTargetDefinition != null)
      {
        Job job = new ImportProjectsJob();
        job.schedule();
      }
    }
  };

  private WorkspaceIUImporter()
  {
  }

  public void updateWorkspace(final IProgressMonitor monitor) throws CoreException
  {
    TargetPlatformUtil.runWithTargetPlatformService(new TargetPlatformRunnable<Object>()
    {
      public Object run(ITargetPlatformService service) throws CoreException
      {
        Set<WorkspaceIUInfo> workspaceIUInfos = new HashSet<WorkspaceIUInfo>();
        List<Pair<TargletContainer, TargletContainerDescriptor>> targletContainerInfos = new ArrayList<Pair<TargletContainer, TargletContainerDescriptor>>();

        ITargetDefinition targetDefinition = TargetPlatformUtil.getActiveTargetDefinition(service);
        if (targetDefinition != null)
        {
          ITargetLocation[] targetLocations = targetDefinition.getTargetLocations();
          if (targetLocations != null)
          {
            for (ITargetLocation location : targetLocations)
            {
              if (location instanceof TargletContainer)
              {
                TargletContainer container = (TargletContainer)location;
                TargletContainerDescriptor descriptor = container.getDescriptor();
                if (descriptor != null && descriptor.getUpdateProblem() == null)
                {
                  targletContainerInfos.add(Pair.create(container, descriptor));

                  Collection<WorkspaceIUInfo> workingProjects = descriptor.getWorkingProjects();
                  if (workingProjects != null)
                  {
                    workspaceIUInfos.addAll(workingProjects);
                  }
                }
              }
            }
          }
        }

        Map<WorkspaceIUInfo, ResourcesUtil.ImportResult> importResults;
        if (workspaceIUInfos.isEmpty())
        {
          importResults = new HashMap<WorkspaceIUInfo, ResourcesUtil.ImportResult>();
        }
        else
        {
          importResults = updateWorkspace(workspaceIUInfos, monitor);
        }

        for (Pair<TargletContainer, TargletContainerDescriptor> pair : targletContainerInfos)
        {
          TargletContainer container = pair.getElement1();
          TargletContainerDescriptor descriptor = pair.getElement2();

          Map<WorkspaceIUInfo, ResourcesUtil.ImportResult> containerImportResults = new HashMap<WorkspaceIUInfo, ResourcesUtil.ImportResult>(importResults);
          containerImportResults.keySet().retainAll(descriptor.getWorkingProjects());

          TargletContainerEvent event = new WorkspaceUpdateFinishedEvent(container, descriptor, containerImportResults);
          TargletContainerListenerRegistryImpl.INSTANCE.notifyListeners(event, monitor);
        }

        return null;
      }
    });
  }

  private Map<WorkspaceIUInfo, ResourcesUtil.ImportResult> updateWorkspace(final Set<WorkspaceIUInfo> workspaceIUInfos, IProgressMonitor monitor)
      throws CoreException
  {
    final Map<WorkspaceIUInfo, ResourcesUtil.ImportResult> importResults = new HashMap<WorkspaceIUInfo, ResourcesUtil.ImportResult>();

    ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable()
    {
      public void run(IProgressMonitor monitor) throws CoreException
      {
        for (WorkspaceIUInfo info : workspaceIUInfos)
        {
          ResourcesUtil.ImportResult result = info.importIntoWorkspace(monitor);
          importResults.put(info, result);
        }
      }
    }, monitor);

    return importResults;
  }

  void start()
  {
    TargetPlatformUtil.addListener(listener);
  }

  void stop()
  {
    TargetPlatformUtil.removeListener(listener);
  }

  /**
   * @author Eike Stepper
   */
  private static final class ImportProjectsJob extends Job
  {
    private ImportProjectsJob()
    {
      super("Import projects");
    }

    @Override
    protected IStatus run(IProgressMonitor monitor)
    {
      try
      {
        INSTANCE.updateWorkspace(monitor);
        return Status.OK_STATUS;
      }
      catch (Exception ex)
      {
        return TargletsCorePlugin.INSTANCE.getStatus(ex);
      }
    }
  }
}
